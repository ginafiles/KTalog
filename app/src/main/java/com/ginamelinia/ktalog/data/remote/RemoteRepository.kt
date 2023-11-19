package com.ginamelinia.ktalog.data.remote

import com.ginamelinia.ktalog.data.model.Drama
import com.ginamelinia.ktalog.data.model.Genre
import com.ginamelinia.ktalog.data.remote.service.TMDBApiService
import com.ginamelinia.ktalog.domain.repository.HomeRepository

class RemoteRepository(private val apiService: TMDBApiService) : HomeRepository{

    override suspend fun getTvGenres(): List<Genre>? {
        return try {
            val response = apiService.getTvGenres()
            if (response.isSuccessful) {
                response.body()?.genres
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getTvShows(country: String): List<Drama>? {
        return try {
            val response = apiService.getTvShows(country)
            if (response.isSuccessful) {
                response.body()?.results?.filter { drama ->
                    !drama.name.isNullOrBlank() && !drama.overview.isNullOrBlank()
                }
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getTvShowsByGenre(genreId: Int): List<Drama>? {
        return try {
            val response = apiService.getTvShowsByGenre(genreId, "KR")
            if (response.isSuccessful) {
                val dramas = response.body()?.results
                dramas?.filter { drama ->
                    !drama.name.isNullOrBlank() && !drama.overview.isNullOrBlank()
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
