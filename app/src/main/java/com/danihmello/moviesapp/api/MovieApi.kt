package com.danihmello.moviesapp.api

import MovieResponse
import com.danihmello.moviesapp.data.Movie
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit API interface
interface MovieApi {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): PopularMoviesResponse

}