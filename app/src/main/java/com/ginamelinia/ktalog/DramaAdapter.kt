package com.ginamelinia.ktalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ginamelinia.ktalog.databinding.ItemDramaBinding

class DramaAdapter() : ListAdapter<Drama, DramaViewHolder>(
    DramaDiffCallback()
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DramaViewHolder {
        val binding = ItemDramaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DramaViewHolder(
            binding = binding
        )
    }

    override fun onBindViewHolder(holder: DramaViewHolder, position: Int) {
        val drama = getItem(position)
        holder.bind(drama)
    }
}