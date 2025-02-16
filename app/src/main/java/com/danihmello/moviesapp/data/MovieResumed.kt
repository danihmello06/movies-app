package com.danihmello.moviesapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResumed(
    val id: Int,
    val posterPath: String? = null,
    val posterFullLink: String? = null,
    val title: String? = null,
) : Parcelable