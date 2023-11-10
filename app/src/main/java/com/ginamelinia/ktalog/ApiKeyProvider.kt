package com.ginamelinia.ktalog

import android.content.Context

object ApiKeyProvider {
    fun getTmdbApiKey(context: Context): String {
        val resources = context.resources
        return resources.getString(R.string.tmdb_api_key)
    }
}