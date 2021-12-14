package com.sirahi.movieapp.data.remote.dto.movie

data class MovieDto(
    val page: Int,
    val results: List<MovieResultDto>,
    val total_pages: Int,
    val total_results: Int
)