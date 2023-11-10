package com.ginamelinia.ktalog

import androidx.recyclerview.widget.DiffUtil

class DramaDiffCallback : DiffUtil.ItemCallback<Drama>() {
    override fun areItemsTheSame(oldItem: Drama, newItem: Drama): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Drama, newItem: Drama): Boolean {
       return oldItem == newItem
    }
}