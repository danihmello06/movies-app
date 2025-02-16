package com.danihmello.moviesapp.presentation.home

import com.danihmello.moviesapp.data.Movie

class HomeState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null
){
    companion object {
        val initial = HomeState()
    }

}
