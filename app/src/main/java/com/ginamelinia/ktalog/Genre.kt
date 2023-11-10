package com.ginamelinia.ktalog

import com.google.gson.annotations.SerializedName

data class Genre (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val genreTitle: String,
)