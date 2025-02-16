package com.danihmello.moviesapp.presentation.home

import com.danihmello.moviesapp.data.Movie
import com.danihmello.moviesapp.data.MovieResumed

data class HomeState(
    val isLoading: Boolean = false,
    val upComingMovies: List<MovieResumed> = emptyList(),
    val popularMovies: List<MovieResumed> = emptyList(),
    val detailsMovie: Movie = Movie(),
    val errorMessage: String? = null
){
    companion object {
        val initial = HomeState()
    }

}
