package com.danihmello.moviesapp.api

import com.danihmello.moviesapp.BuildConfig
import com.danihmello.moviesapp.data.Movie
import com.danihmello.moviesapp.data.MovieResumed
import com.danihmello.moviesapp.domain.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieApi
): MovieRepository {

    override suspend fun getPopularMovies(): Flow<List<MovieResumed>> = flow {
        val popularMoviesResponse = service.getPopularMovies(BuildConfig.API_KEY)
        emit(MoviesMapper.mapMovieResumed(popularMoviesResponse))
    }

    override suspend fun getUpcomingMovies(): Flow<List<MovieResumed>> = flow {
        val upcomingMoviesResponse = service.getUpcomingMovies(BuildConfig.API_KEY)
        emit(MoviesMapper.mapMovieResumed(upcomingMoviesResponse))
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<Movie> = flow {
        val movieDetailsResponse = service.getMovieDetails(movieId, BuildConfig.API_KEY)
        emit(MoviesMapper.mapMovieDetails(movieDetailsResponse))
    }
}