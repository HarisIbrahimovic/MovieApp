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
            posterPath = poster_path!!,
            category = category,
            type = type,
            title = title,
            score = vote_average,
            overview = overview!!,
            releaseDate = release_date!!,
            backdropPath = backdrop_path!!
        )
    }

    fun toMediaResult(type: String): MediaResult {
        return MediaResult(
            id = id,
            posterPath = poster_path!!,
            type = type,
            score = vote_average,
            overview = overview!!,
            releaseDate = release_date!!,
            title = title,
            backdropPath = backdrop_path!!
        )
    }

    fun checkPosterPath() {
        if (poster_path == null) poster_path = "No photo"
        if (release_date == null) release_date = "No date"
        if (overview == null) overview = "No overview"
        if (backdrop_path == null) backdrop_path = "No photo"
    }
}
