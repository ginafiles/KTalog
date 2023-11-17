package com.ginamelinia.ktalog.presentation.adapter.drama

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ginamelinia.ktalog.data.model.Drama
import com.ginamelinia.ktalog.databinding.ItemDramaBinding

class DramaAdapter(
    private val onItemClick: ((Drama) -> Unit)?
) : ListAdapter<Drama, DramaViewHolder>(
    DramaDiffCallback()
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DramaViewHolder {
        val binding = ItemDramaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DramaViewHolder(
            binding = binding,
            onItemClick = onItemClick
        )
    }

    override fun onBindViewHolder(holder: DramaViewHolder, position: Int) {
        val drama = getItem(position)
        holder.bind(drama)
    }
}