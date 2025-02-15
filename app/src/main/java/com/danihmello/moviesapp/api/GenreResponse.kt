package com.danihmello.moviesapp.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)