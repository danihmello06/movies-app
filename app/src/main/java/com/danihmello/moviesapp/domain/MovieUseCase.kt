package com.danihmello.moviesapp.domain

import com.danihmello.moviesapp.data.Movie
import com.danihmello.moviesapp.data.MovieResumed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val repository: MovieRepository
){

    suspend fun getPopularMovies(): Flow<List<MovieResumed>> {
        return repository.getPopularMovies()
    }

    suspend fun getUpcomingMovies(): Flow<List<MovieResumed>> {
        return repository.getUpcomingMovies()
    }

    suspend fun getMovieDetails(movieId: Int): Flow<Movie> {
        return repository.getMovieDetails(movieId)
    }
}