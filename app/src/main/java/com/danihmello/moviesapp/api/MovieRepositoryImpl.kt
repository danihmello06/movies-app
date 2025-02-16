package com.danihmello.moviesapp.api

import com.danihmello.moviesapp.BuildConfig
import com.danihmello.moviesapp.data.Movie
import com.danihmello.moviesapp.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieApi
): MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        val popularMoviesResponse = service.getPopularMovies(BuildConfig.API_KEY)
        return MoviesMapper.mapPopularMoviesResponse(popularMoviesResponse)
    }
}