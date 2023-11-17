package com.ginamelinia.ktalog.presentation.adapter.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ginamelinia.ktalog.data.model.Genre
import com.ginamelinia.ktalog.databinding.ItemGenreBinding

class GenreAdapter() : ListAdapter<Genre, GenreViewHolder>(
    GenreDiffCallback()
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(
            binding = binding
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = getItem(position)
        holder.bind(genre)
    }
}