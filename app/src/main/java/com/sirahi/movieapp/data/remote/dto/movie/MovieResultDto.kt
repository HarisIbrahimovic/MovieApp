package com.sirahi.movieapp.data.remote.dto.movie

import com.sirahi.movieapp.data.local.entity.MediaResultEntity
import com.sirahi.movieapp.model.MediaResult

data class MovieResultDto(
    val adult: Boolean,
    var backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    var overview: String?,
    val popularity: Double,
    var poster_path: String?,
    var release_date: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toMediaResultEntity(category: String, type: String): MediaResultEntity {
        return MediaResultEntity(
            mediaId = id,
            posterPath = poster_path?:"No picture",
            category = category,
            type = type,
            title = title,
            score = vote_average,
            overview = overview?: "No overview",
            releaseDate = release_date?:"No data",
            backdropPath = backdrop_path?:"No picture"
        )
    }

    fun toMediaResult(type: String): MediaResult {
        return MediaResult(
            id = id,
            posterPath = poster_path?:"No picture",
            type = type,
            score = vote_average,
            overview = overview?:"No picture",
            releaseDate = release_date?:"No data",
            title = title,
            backdropPath = backdrop_path?:"No picture"
        )
    }

}
