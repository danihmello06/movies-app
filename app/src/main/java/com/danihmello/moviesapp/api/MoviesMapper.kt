package com.danihmello.moviesapp.api

import com.danihmello.moviesapp.data.Movie

object MoviesMapper {

    fun mapPopularMoviesResponse(response: PopularMoviesResponse): List<Movie> {
        return response.results.map { movieResponse ->
            Movie(
                imageBackdropPath = movieResponse.imageBackdropPath,
                budget = movieResponse.budget,
                genres = movieResponse.genres?.map { genre ->
                    Movie.Genre(
                        id = genre.id,
                        name = genre.name
                    )
                },
                homepage = movieResponse.homepage,
                id = movieResponse.id,
                imdbId = movieResponse.imdbId,
                originCountry = movieResponse.originCountry,
                originalLanguage = movieResponse.originalLanguage,
                originalTitle = movieResponse.originalTitle,
                overview = movieResponse.overview,
                popularity = movieResponse.popularity,
                posterPath = movieResponse.posterPath,
                releaseDate = movieResponse.releaseDate,
                revenue = movieResponse.revenue,
                runtime = movieResponse.runtime,
                spokenLanguages = movieResponse.spokenLanguages,
                status = movieResponse.status,
                tagline = movieResponse.tagline,
                title = movieResponse.title,
                video = movieResponse.video,
                voteAverage = movieResponse.voteAverage,
                voteCount = movieResponse.voteCount
            )
        }
    }
}