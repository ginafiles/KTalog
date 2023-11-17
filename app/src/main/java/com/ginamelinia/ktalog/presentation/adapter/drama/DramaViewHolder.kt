package com.ginamelinia.ktalog.presentation.adapter.drama

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ginamelinia.ktalog.data.model.Drama
import com.ginamelinia.ktalog.databinding.ItemDramaBinding

class DramaViewHolder(
    private val binding: ItemDramaBinding,
    private val onItemClick: ((Drama) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(drama: Drama) {
        binding.tvKdramaName.text = drama.name
        binding.tvKdramaOverview.text = drama.overview

        Glide.with(binding.imageViewKdrama)
            .load("https://image.tmdb.org/t/p/w500" + drama.posterPath)
            .into(binding.imageViewKdrama)

        binding.root.setOnClickListener {
            onItemClick?.invoke(drama)
        }
    }
}