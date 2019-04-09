package com.traning.suriya.comicapp.comic

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.traning.suriya.comicapp.base.BaseTest
import com.traning.suriya.comicapp.scene.comic.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.net.HttpURLConnection
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
class ComicViewModelTest:BaseTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Force tests to be executed synchronously

    private lateinit var activity: FragmentActivity
    private lateinit var viewModel: MainViewModel

    @Before
    override fun setUp() {
        super.setUp()
        this.activity = Robolectric.setupActivity(FragmentActivity::class.java)
        this.viewModel = ViewModelProviders.of(this.activity, factory)[MainViewModel::class.java]
    }

    @Test
    fun getComic_whenSuccess(){
        this.mockHttpResponse("comic_mock.json", HttpURLConnection.HTTP_OK)
        this.mockHttpResponse("banner_mock.json", HttpURLConnection.HTTP_OK)
        assertNotNull(this.viewModel.getBannerLiveData().value,"User should be null because stream not started yet")
        this.viewModel.getBanner()
        this.viewModel.getBannerLiveData().observe(activity, Observer {
            if (it is MainViewModel.Status.Success){
                assertNotNull(it.viewDataBundle.result, "Respnce not null")
                assertEquals(0, it.viewDataBundle.result?.banner?.data!![0].bannerId)
            }
        })
    }
}