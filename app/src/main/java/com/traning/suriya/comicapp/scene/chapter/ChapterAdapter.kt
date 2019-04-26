package com.traning.suriya.comicapp.scene.chapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.traning.suriya.comicapp.databinding.ItemChapterBinding
import com.traning.suriya.comicapp.model.chapter.Chapter

class ChapterAdapter(private val list: List<Chapter>, private val onItemClickListener: OnChapterClick) : RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {

    private lateinit var binding: ItemChapterBinding

    fun onItemClick(chapter: Chapter){
        onItemClickListener.onItemClick(chapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ChapterViewHolder {
        binding = ItemChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.adapter = this
        return ChapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class ChapterViewHolder(private val holderBinding: ItemChapterBinding) : RecyclerView.ViewHolder(holderBinding.root){

        fun bind(chapter: Chapter) {
            holderBinding.model = chapter
            holderBinding.executePendingBindings()
        }
    }

    interface OnChapterClick{
        fun onItemClick(chapter: Chapter)
    }
}