package com.sirahi.movieapp.data.remote.dto.credits

data class MediaCreditsDto(
    val cast: List<MovieCreditDto>,
    val id: Int
)