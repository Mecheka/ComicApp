package com.traning.suriya.comicapp.scene.comic

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.traning.suriya.comicapp.base.BaseViewModel
import com.traning.suriya.comicapp.model.combine.BannerAndComic
import com.traning.suriya.comicapp.repository.MainRepository
import com.traning.suriya.comicapp.util.StatusViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {

    private val bannerLiveData = MutableLiveData<Status>()

    init {
        bannerLiveData.value = Status.Stale(ViewDataBundle())
    }

    fun getBanner() {
        bannerLiveData.value = Status.Loading(ViewDataBundle())
        addDisposable(repository.getComicAndBanner()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("Zip Data :", "Load Ok")
                bannerLiveData.value = Status.Success(ViewDataBundle(it))
            },{
                Log.d("Zip Data :", "Load error", it)
                bannerLiveData.value = Status.Error(it?.message!!, ViewDataBundle())
            }))
    }

    fun getBannerLiveData() = bannerLiveData

    sealed class Status(viewDataBundle: ViewDataBundle) : StatusViewModel.Status<ViewDataBundle>(viewDataBundle) {
        class Stale(viewDataBundle: ViewDataBundle) : Status(viewDataBundle)
        class Loading(viewDataBundle: ViewDataBundle) : Status(viewDataBundle)
        class Success(viewDataBundle: ViewDataBundle) : Status(viewDataBundle)
        class Error(val mesage: String, viewDataBundle: ViewDataBundle) : Status(viewDataBundle)
    }

    data class ViewDataBundle(val result: BannerAndComic? = null)
}