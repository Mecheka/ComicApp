package com.traning.suriya.comicapp.scene.viewdetail

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.traning.suriya.comicapp.databinding.ItemPagerChapterBinding
import com.traning.suriya.comicapp.model.link.Link

class PagerChapterAdapter(private val viewDetailList: List<Link>) : PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = viewDetailList.size

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemPagerChapterBinding.inflate(LayoutInflater.from(container.context),
                container, false)
        Picasso.get().load(viewDetailList[position].link).into(binding.chapterImage)
        container.addView(binding.root)
        return binding.root
    }
}