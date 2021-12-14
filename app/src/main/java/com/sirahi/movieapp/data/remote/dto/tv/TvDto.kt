package com.sirahi.movieapp.data.remote.dto.tv

data class TvDto(
    val page: Int,
    val results: List<TVResultDto>,
    val total_pages: Int,
    val total_results: Int
)