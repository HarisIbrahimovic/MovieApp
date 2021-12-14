package com.sirahi.movieapp.model.tv

data class TVDetails(
    val inProduction: Boolean,
    val firstAirDate: String,
    val lastAirDates: String,
    val name: String,
    val nextEpisodeToAir: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val overview: String,
    val status: String,
    val type: String,
    val voteAverage: Int,
    val posterPath: String
)