package com.ginamelinia.ktalog

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ginamelinia.ktalog.databinding.ItemDramaBinding

class DramaViewHolder(
    private val binding: ItemDramaBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(drama: Drama) {
        binding.tvKdramaName.text = drama.name
        binding.tvKdramaOverview.text = drama.overview

        Glide.with(binding.imageViewKdrama)
            .load("https://image.tmdb.org/t/p/w500" + drama.posterPath)
            .into(binding.imageViewKdrama)

        binding.root.setOnClickListener {
        }
    }
}