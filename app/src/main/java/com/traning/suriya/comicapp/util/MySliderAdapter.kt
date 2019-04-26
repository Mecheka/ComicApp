package com.traning.suriya.comicapp.util

import com.traning.suriya.comicapp.model.banner.Banner
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class MySliderAdapter(private val listBanner:List<Banner>): SliderAdapter() {
    override fun getItemCount(): Int = listBanner.size

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {
        imageSlideViewHolder?.bindImageSlide(listBanner[position].link)
    }
}