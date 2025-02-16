package com.danihmello.moviesapp.domain

import com.danihmello.moviesapp.data.Movie
import com.danihmello.moviesapp.data.MovieResumed
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(): Flow<List<MovieResumed>>

    suspend fun getUpcomingMovies(): Flow<List<MovieResumed>>

    suspend fun getMovieDetails(movieId: Int): Flow<Movie>
}