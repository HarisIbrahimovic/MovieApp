package com.sirahi.movieapp.data.remote.dto.credits

data class MovieCreditsDto(
    val cast: List<MovieCastDto>,
    val id: Int
)