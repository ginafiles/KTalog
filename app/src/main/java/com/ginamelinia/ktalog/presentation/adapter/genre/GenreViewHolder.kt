package com.ginamelinia.ktalog.presentation.adapter.genre

import androidx.recyclerview.widget.RecyclerView
import com.ginamelinia.ktalog.data.model.Genre
import com.ginamelinia.ktalog.databinding.ItemGenreBinding

class GenreViewHolder(
    private val binding: ItemGenreBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: Genre) {
        binding.genreTitle.text = genre.genreTitle

        binding.root.setOnClickListener {

        }
    }
}