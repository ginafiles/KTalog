package com.ginamelinia.ktalog

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenreViewModel : ViewModel() {
    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList

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
                    // Handle error response here
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle exception here
            }
        }
    }
}
