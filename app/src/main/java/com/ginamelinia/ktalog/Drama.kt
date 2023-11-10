package com.ginamelinia.ktalog

import com.google.gson.annotations.SerializedName

data class Drama(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String
)