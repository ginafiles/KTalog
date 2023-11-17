package com.ginamelinia.ktalog.presentation.adapter.drama

import androidx.recyclerview.widget.DiffUtil
import com.ginamelinia.ktalog.data.model.Drama

class DramaDiffCallback : DiffUtil.ItemCallback<Drama>() {
    override fun areItemsTheSame(oldItem: Drama, newItem: Drama): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Drama, newItem: Drama): Boolean {
       return oldItem == newItem
    }
}