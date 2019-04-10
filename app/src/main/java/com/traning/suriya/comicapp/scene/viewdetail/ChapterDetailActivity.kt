package com.traning.suriya.comicapp.scene.viewdetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.traning.suriya.comicapp.R
import com.traning.suriya.comicapp.databinding.ActivityChapterDetailBinding
import com.traning.suriya.comicapp.model.chapter.Chapter
import org.koin.android.viewmodel.ext.android.viewModel

class ChapterDetailActivity : AppCompatActivity() {

    private val viewModel: ChapterDetailViewModel by viewModel()
    private lateinit var binding: ActivityChapterDetailBinding
    val chapter: Chapter by lazy {
        intent.getParcelableExtra("chapter") as Chapter
    }
    private var chapterIndex: Int = 0
    private var chapterListSize: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chapter_detail)
        binding.model = chapter
        binding.viewControler = this
        initData()
        binding.pagerChapter.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                chapterIndex = position
            }
        })
    }

    private fun initData() {
        viewModel.detailLiveData.observe(this, Observer { dataSource ->
            when (dataSource) {
                is ChapterDetailViewModel.Status.Stale -> {
                    binding.layoutLoading.visibility = View.VISIBLE
                }
                is ChapterDetailViewModel.Status.Loading -> {
                    binding.layoutLoading.visibility = View.VISIBLE
                }
                is ChapterDetailViewModel.Status.Success -> {
                    binding.layoutLoading.visibility = View.GONE
                    val adapter = PagerChapterAdapter(dataSource.viewDataBundle.result)
                    chapterListSize = dataSource.viewDataBundle.result.size
                    binding.pagerChapter.adapter = adapter
                }
                is ChapterDetailViewModel.Status.SuccessWithNoData -> {
                    binding.layoutLoading.visibility = View.GONE
                    binding.layoutNoData.visibility = View.VISIBLE
                    binding.pagerChapter.visibility = View.GONE
                }
                is ChapterDetailViewModel.Status.Error -> {
                    binding.layoutLoading.visibility = View.GONE
                    binding.layoutNoData.visibility = View.VISIBLE
                    binding.pagerChapter.visibility = View.GONE
                    Toast.makeText(this, dataSource.mesage, Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.getChapterDetailByChapterId(chapter.id)
    }

    fun onBackPagerClick(view: View) {
        if (chapterIndex == 0) {
            Toast.makeText(this, "Yor are reading first chapter", Toast.LENGTH_LONG).show()
        } else {
            binding.pagerChapter.currentItem = chapterIndex - 1
        }
    }

    fun onNextPagerClick(view: View) {
        if (chapterIndex == chapterListSize) {
            Toast.makeText(this, "Yor are reading last chapter", Toast.LENGTH_LONG).show()
        } else {
            binding.pagerChapter.currentItem = chapterIndex + 1
        }
    }
}
