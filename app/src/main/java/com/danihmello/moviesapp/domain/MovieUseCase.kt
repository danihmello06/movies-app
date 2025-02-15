package com.danihmello.moviesapp.domain

import com.danihmello.moviesapp.data.Movie
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val repository: MovieRepository
){

    suspend fun getPopularMovies(): List<Movie> {
        return repository.getPopularMovies()
    }
}