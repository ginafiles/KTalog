package com.ginamelinia.ktalog

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList
    private val _dramaList = MutableLiveData<List<Drama>>()
    val dramaList: LiveData<List<Drama>> = _dramaList

    fun loadGenres(context: Context) {
        val retrofit = RetrofitClient.create(context)
        val apiService = retrofit.create(TMDBApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getTvGenres()
                if (response.isSuccessful) {
                    val genres = response.body()?.genres
                    withContext(Dispatchers.Main) {
                        genres?.let {
                            _genreList.value = it
                        }
                    }
                } else {
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadDramas(context: Context) {
        val retrofit = RetrofitClient.create(context)
        val apiService = retrofit.create(TMDBApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getTvShows("KR")
                if (response.isSuccessful) {
                    val dramas = response.body()?.results
                    withContext(Dispatchers.Main) {
                        dramas?.let {
                            val filteredDramas = it.filter { drama ->
                                !drama.name.isNullOrBlank() && !drama.overview.isNullOrBlank()
                            }
                            _dramaList.value = filteredDramas
                        }
                    }
                } else {
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
