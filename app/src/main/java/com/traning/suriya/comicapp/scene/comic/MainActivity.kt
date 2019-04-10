package com.traning.suriya.comicapp.scene.comic

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.traning.suriya.comicapp.R
import com.traning.suriya.comicapp.databinding.ActivityMainBinding
import com.traning.suriya.comicapp.model.comic.Comic
import com.traning.suriya.comicapp.model.comic.ComicResponce
import com.traning.suriya.comicapp.repository.MainRepository
import com.traning.suriya.comicapp.scene.chapter.ChapterActivity
import com.traning.suriya.comicapp.util.MySliderAdapter
import com.traning.suriya.comicapp.util.PicassoImageLoadingService
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ss.com.bannerslider.Slider

class MainActivity : AppCompatActivity(), ComicAdapter.OnItemComicClick {

    private val repository: MainRepository by currentScope.inject()
    private val viewModel: MainViewModel by viewModel { parametersOf(repository) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Slider.init(PicassoImageLoadingService())

        binding.recyclerComic.setHasFixedSize(true)
        binding.recyclerComic.layoutManager = GridLayoutManager(this, 2)

        initData()
    }

    private fun initData() {
        viewModel.getBannerLiveData().observe(this, Observer { dataSource ->
            when (dataSource) {
                is MainViewModel.Status.Loading -> {
                    binding.layoutLoading.visibility = View.VISIBLE
                }
                is MainViewModel.Status.Success -> {
                    binding.layoutLoading.visibility = View.GONE
                    binding.textComic.text = String.format(
                        getString(
                            R.string.new_comic,
                            dataSource.viewDataBundle.result?.comic?.data?.size.toString()
                        )
                    )
                    binding.bannerSlider.setAdapter(dataSource.viewDataBundle.result?.banner?.data?.let {
                        MySliderAdapter(it)
                    })
                    initDataRecyclerView(dataSource.viewDataBundle.result?.comic)
                }
                is MainViewModel.Status.Error -> {
                    Toast.makeText(this, dataSource.mesage, Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.getBanner()
    }

    private fun initDataRecyclerView(comic: ComicResponce?) {
        val adapter = comic?.data?.let {
            ComicAdapter(it, this)
        }
        binding.recyclerComic.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun onItemClick(comic: Comic) {
        Intent().apply {
            this.setClass(this@MainActivity, ChapterActivity::class.java)
            this.putExtra("comic", comic)
            startActivity(this)
        }
    }
}
