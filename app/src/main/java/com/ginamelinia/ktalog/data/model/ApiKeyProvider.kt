package com.ginamelinia.ktalog.data.model

import android.content.Context
import com.ginamelinia.ktalog.R

object ApiKeyProvider {
    fun getTmdbApiKey(context: Context): String {
        val resources = context.resources
        return resources.getString(R.string.tmdb_api_key)
    }
}