package com.danihmello.moviesapp.domain

import com.danihmello.moviesapp.data.Movie

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>

}