package com.sirahi.movieapp.data.remote.dto.movie

import com.sirahi.movieapp.data.local.entity.movie.MovieDetailsEntity

data class MovieDetailsDto(
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toMovieDetailsEntity(): MovieDetailsEntity {
        return MovieDetailsEntity(
            id = id,
            voteAverage = vote_average,
            backdropPath = backdrop_path,
            title = title,
            runtime = runtime,
            posterPath = poster_path,
            releaseDate = release_date,
            overview = overview,
        )
    }
}