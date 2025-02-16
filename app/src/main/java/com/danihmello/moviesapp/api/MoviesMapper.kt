package com.danihmello.moviesapp.api

import MovieCommonListResponse
import MovieResponse
import com.danihmello.moviesapp.data.Movie
import com.danihmello.moviesapp.data.MovieResumed

object MoviesMapper {

    fun mapMovieDetails(response: MovieResponse): Movie {
        return Movie(
            imageBackdropPath = response.imageBackdropPath,
            budget = response.budget,
            genres = response.genres?.map { genre ->
                Movie.Genre(
                    id = genre.id,
                    name = genre.name
                )
            },
            homepage = response.homepage,
            id = response.id,
            imdbId = response.imdbId,
            originCountry = response.originCountry,
            originalLanguage = response.originalLanguage,
            originalTitle = response.originalTitle,
            overview = response.overview,
            popularity = response.popularity,
            posterPath = response.posterPath,
            posterFullLink = ApiConstants.IMAGE_BASE_URL+response.posterPath,
            releaseDate = response.releaseDate,
            revenue = response.revenue,
            runtime = response.runtime,
            spokenLanguages = response.spokenLanguages,
            status = response.status,
            tagline = response.tagline,
            title = response.title,
            video = response.video,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount
        )
    }

    fun mapMovieResumed(response: MovieCommonListResponse): List<MovieResumed> {
        return response.results.map { movieResponse ->
            MovieResumed(
                id = movieResponse.id,
                posterPath = movieResponse.posterPath,
                posterFullLink = ApiConstants.IMAGE_BASE_URL+movieResponse.posterPath,
                title = movieResponse.title,
            )
        }
    }
}