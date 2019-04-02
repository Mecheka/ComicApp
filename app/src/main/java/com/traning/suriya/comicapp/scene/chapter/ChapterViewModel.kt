package com.traning.suriya.comicapp.scene.chapter

import android.arch.lifecycle.MutableLiveData
import com.traning.suriya.comicapp.base.BaseViewModel
import com.traning.suriya.comicapp.model.chapter.Chapter
import com.traning.suriya.comicapp.repository.ChapterRepository
import com.traning.suriya.comicapp.service.ComicAPI
import com.traning.suriya.comicapp.util.StatusViewModel
import javax.inject.Inject

class ChapterViewModel @Inject constructor(service: ComicAPI) : BaseViewModel() {

    private val repository = ChapterRepository(service)

    val chapterLiveData = MutableLiveData<Status>()

    init {
        chapterLiveData.value = Status.Stale(ViewDataBundle())
    }

    fun getChapter(mangaId: Int) {
        chapterLiveData.value = Status.Loading(ViewDataBundle())
        addDisposable(
                repository.getChapterByComicId(mangaId)
                        .subscribe({ responce ->
                            if (responce.code() == 200) {
                                responce.body()?.data?.let {
                                    chapterLiveData.value = Status.Success(ViewDataBundle(it))
                                } ?: run {
                                    chapterLiveData.value = Status.SuccessWithNoData(ViewDataBundle())
                                }
                            } else {
                                chapterLiveData.value = Status.SuccessWithNoData(ViewDataBundle())
                            }
                        }, {
                            chapterLiveData.value = Status.Error(it.message!!, ViewDataBundle())
                        })
        )
    }

    sealed class Status(viewDataBundle: ViewDataBundle) : StatusViewModel.Status<ViewDataBundle>(viewDataBundle) {
        class Stale(viewDataBundle: ViewDataBundle) : Status(viewDataBundle)
        class Loading(viewDataBundle: ViewDataBundle) : Status(viewDataBundle)
        class Success(viewDataBundle: ViewDataBundle) : Status(viewDataBundle)
        class SuccessWithNoData(viewDataBundle: ViewDataBundle) : Status(viewDataBundle)
        class Error(val mesage: String, viewDataBundle: ViewDataBundle) : Status(viewDataBundle)
    }

    data class ViewDataBundle(val result: List<Chapter> = listOf())
}