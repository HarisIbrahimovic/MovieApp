package com.sirahi.movieapp.model.tv

data class TvCast(
    val character: String,
    val episodeCount: Int,
    val firstAirDate: String,
    val id: Int,
    val name: String,
    val posterPath: String,
    val voteAverage: Double,
)