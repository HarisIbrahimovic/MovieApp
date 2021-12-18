package com.sirahi.movieapp.model.movie

data class MovieDetails(
    val releaseDate: String,
    val voteAverage:Double,
    val title: String,
    val runtime: Int,
    val posterPath: String,
    val overview: String,
    val backdropPath: String
)