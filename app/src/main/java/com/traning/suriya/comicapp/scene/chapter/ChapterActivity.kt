package com.traning.suriya.comicapp.scene.chapter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.traning.suriya.comicapp.R
import com.traning.suriya.comicapp.databinding.ActivityChapterBinding
import com.traning.suriya.comicapp.model.chapter.Chapter
import com.traning.suriya.comicapp.model.comic.Comic
import com.traning.suriya.comicapp.scene.viewdetail.ChapterDetailActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChapterActivity : DaggerAppCompatActivity(), ChapterAdapter.OnChapterClick {

    private val comic: Comic by lazy {
        intent.getParcelableExtra("comic") as Comic
    }

    private lateinit var binding: ActivityChapterBinding
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: ChapterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chapter)
        viewModel = ViewModelProviders.of(this, factory).get(ChapterViewModel::class.java)

        initInstance()
        initData()
    }

    private fun initInstance() {
        binding.toolbar.title = comic.name
        binding.toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        binding.chapterRecyclerview.apply {
            this.setHasFixedSize(true)
            this.layoutManager = layoutManager
            this.addItemDecoration(DividerItemDecoration(this@ChapterActivity, layoutManager.orientation))
        }
    }

    private fun initData() {

        viewModel.chapterLiveData.observe(this, Observer { dataSource ->
            when (dataSource) {
                is ChapterViewModel.Status.Loading -> {
                    binding.layoutLoading.visibility = View.VISIBLE
                }
                is ChapterViewModel.Status.Success -> {
                    binding.layoutLoading.visibility = View.GONE
                    binding.textChapter.text = String.format(getString(R.string.chapter, dataSource.viewDataBundle.result.size.toString()))
                    initDataRecyclerview(dataSource.viewDataBundle.result)
                }
                is ChapterViewModel.Status.SuccessWithNoData->{
                    binding.layoutLoading.visibility = View.GONE
                    binding.chapterRecyclerview.visibility = View.GONE
                    binding.textChapter.text = String.format(getString(R.string.chapter, "0"))

                    binding.layoutNoData.visibility = View.VISIBLE
                }
                is ChapterViewModel.Status.Error -> {
                    binding.layoutLoading.visibility = View.GONE
                    binding.chapterRecyclerview.visibility = View.GONE
                    binding.layoutNoData.visibility = View.VISIBLE
                    Toast.makeText(this, dataSource.mesage, Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.getChapter(comic.mangaId)
    }

    private fun initDataRecyclerview(result: List<Chapter>) {
        val adapter = ChapterAdapter(result,this)
        binding.chapterRecyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(chapter: Chapter) {
        Intent().apply {
            this.setClass(this@ChapterActivity, ChapterDetailActivity::class.java)
            this.putExtra("chapter", chapter)
            startActivity(this)
        }
    }
}
