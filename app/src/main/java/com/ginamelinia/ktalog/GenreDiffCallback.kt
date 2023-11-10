package com.ginamelinia.ktalog

import androidx.recyclerview.widget.DiffUtil

class GenreDiffCallback : DiffUtil.ItemCallback<Genre>(){
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.genreTitle == newItem.genreTitle
    }

}