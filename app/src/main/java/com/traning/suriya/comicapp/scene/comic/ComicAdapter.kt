package com.traning.suriya.comicapp.scene.comic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.traning.suriya.comicapp.databinding.ItemComicBinding
import com.traning.suriya.comicapp.model.comic.Comic

class ComicAdapter(private val list: List<Comic>, private val onClickListener: OnItemComicClick) : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>(){

    lateinit var binding: ItemComicBinding

    fun onItemClick(){
        onClickListener.onItenClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ComicViewHolder {
        binding = ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ComicViewHolder(val holderBinding: ItemComicBinding): RecyclerView.ViewHolder(holderBinding.root){

        fun bind(comic: Comic) {
            holderBinding.model = comic
            holderBinding.executePendingBindings()
            Picasso.get().load(comic.image).into(holderBinding.imageComic)
        }
    }

    interface OnItemComicClick{
        fun onItenClick()
    }
}