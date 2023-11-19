package com.ginamelinia.ktalog.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val genreTitle: String,
): Parcelable