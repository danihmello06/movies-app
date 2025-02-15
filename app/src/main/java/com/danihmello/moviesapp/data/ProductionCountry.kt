package com.danihmello.moviesapp.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCountry(
    val iso31661: String,
    val name: String
):Parcelable