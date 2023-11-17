package com.ginamelinia.ktalog.domain.repository

import com.ginamelinia.ktalog.data.model.Drama
import com.ginamelinia.ktalog.data.model.Genre

interface HomeRepository {
    suspend fun getTvGenres(): List<Genre>?
    suspend fun getTvShows(country: String): List<Drama>?
}