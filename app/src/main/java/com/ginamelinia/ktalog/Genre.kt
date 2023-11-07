package com.ginamelinia.ktalog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre (
    val genreTitle: String,
): Parcelable