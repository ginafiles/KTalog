package com.ginamelinia.ktalog.presentation.adapter.genre

import androidx.recyclerview.widget.DiffUtil
import com.ginamelinia.ktalog.data.model.Genre

class GenreDiffCallback : DiffUtil.ItemCallback<Genre>(){
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.genreTitle == newItem.genreTitle
    }

}