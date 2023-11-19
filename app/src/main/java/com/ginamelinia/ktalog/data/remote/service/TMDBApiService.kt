package com.ginamelinia.ktalog.data.remote.service

import com.ginamelinia.ktalog.data.remote.response.DramaResponse
import com.ginamelinia.ktalog.data.remote.response.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApiService {
    @GET("genre/tv/list")
    suspend fun getTvGenres(): Response<GenreResponse>

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("with_origin_country") country: String,
    ): Response<DramaResponse>

    @GET("discover/tv")
    suspend fun getTvShowsByGenre(
        @Query("with_genres") genreId: Int,
        @Query("with_origin_country") country: String,
    ): Response<DramaResponse>
}