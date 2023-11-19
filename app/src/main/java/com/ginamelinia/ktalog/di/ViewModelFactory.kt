package com.ginamelinia.ktalog.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ginamelinia.ktalog.HomeViewModel
import com.ginamelinia.ktalog.domain.repository.HomeRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val homeRepository: HomeRepository?) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                if (homeRepository != null) {
                    HomeViewModel(homeRepository) as T
                } else {
                    throw IllegalArgumentException("HomeRepository must not be null for HomeViewModel")
                }
            }
//            modelClass.isAssignableFrom(DramaViewModel::class.java) -> {
//                if (dramaRepository != null) {
//                    DramaViewModel(dramaRepository) as T
//                } else {
//                    throw IllegalArgumentException("DramaRepository must not be null for DramaViewModel")
//                }
//            }
            // Tambahkan kelas ViewModel lainnya di sini jika diperlukan
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}






