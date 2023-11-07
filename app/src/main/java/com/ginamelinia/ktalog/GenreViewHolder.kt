package com.ginamelinia.ktalog

import androidx.recyclerview.widget.RecyclerView
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