package com.ginamelinia.ktalog

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApiService {
    @GET("genre/tv/list")
    suspend fun getTvGenres(): Response<GenreResponse>

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("with_origin_country") country: String,
//        @Query("with_genres") genreId: Int
    ): Response<DramaResponse>
}