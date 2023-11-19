package com.ginamelinia.ktalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ginamelinia.ktalog.data.model.Drama
import com.ginamelinia.ktalog.data.model.Genre
import com.ginamelinia.ktalog.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList

    private val _dramaList = MutableLiveData<List<Drama>>()
    val dramaList: LiveData<List<Drama>> = _dramaList

    fun loadGenres() {
        CoroutineScope(Dispatchers.IO).launch {
            val genres = repository.getTvGenres()
            withContext(Dispatchers.Main) {
                genres?.let {
                    _genreList.value = it
                }
            }
        }
    }

    fun loadDramas() {
        CoroutineScope(Dispatchers.IO).launch {
            val dramas = repository.getTvShows("KR")
            withContext(Dispatchers.Main) {
                dramas?.let {
                    _dramaList.value = it
                }
            }
        }
    }

    fun loadDramasByGenre(genreId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val dramas = repository.getTvShowsByGenre(genreId)
                withContext(Dispatchers.Main) {
                    _dramaList.value = dramas!!
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

