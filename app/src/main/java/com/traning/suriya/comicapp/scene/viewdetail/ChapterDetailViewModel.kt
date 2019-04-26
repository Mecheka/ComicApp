package com.traning.suriya.comicapp.scene.viewdetail

import android.arch.lifecycle.MutableLiveData
import com.traning.suriya.comicapp.base.BaseViewModel
import com.traning.suriya.comicapp.model.combine.BannerAndComic
import com.traning.suriya.comicapp.model.link.Link
import com.traning.suriya.comicapp.repository.ChapterDetailRepository
import com.traning.suriya.comicapp.service.ComicAPI
import com.traning.suriya.comicapp.util.StatusViewModel
import javax.inject.Inject

class ChapterDetailViewModel @Inject constructor(service: ComicAPI) : BaseViewModel() {

    private val repository = ChapterDetailRepository(service)
    val detailLiveData = MutableLiveData<Status>()

    init {
        detailLiveData.value = Status.Stale(ViewDataBundle())
    }

    fun getChapterDetailByChapterId(chapterId: Int) {
        detailLiveData.value = Status.Loading(ViewDataBundle())
        addDisposable(repository.getChapterDetailByChapterId(chapterId)
            .subscribe({responce->
                if (responce.code() == 200){
                    responce.body()?.data?.let {
                        detailLiveData.value = Status.Success(ViewDataBundle(it))
                    }?:run {
                        detailLiveData.value = Status.SuccessWithNoData(ViewDataBundle())
                    }
                }else{
                    detailLiveData.value = Status.SuccessWithNoData(ViewDataBundle())
                }
            }, {
                detailLiveData.value = Status.Error(it.message!!, ViewDataBundle())
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

    data class ViewDataBundle(val result: List<Link> = listOf())
}
