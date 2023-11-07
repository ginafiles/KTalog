package com.ginamelinia.ktalog

import androidx.recyclerview.widget.DiffUtil

class GenreDiffCallback : DiffUtil.ItemCallback<Genre>(){
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.genreTitle == newItem.genreTitle
    }

}