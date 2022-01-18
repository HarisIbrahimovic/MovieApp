package com.sirahi.movieapp.data.remote.dto.tv

import com.sirahi.movieapp.data.local.entity.MediaResultEntity
import com.sirahi.movieapp.model.MediaResult

data class TVResultDto(
    var backdrop_path: String?,
    var first_air_date: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    var overview: String?,
    val popularity: Double,
    var poster_path: String?,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toMediaResultEntity(category: String, type: String): MediaResultEntity {
        return MediaResultEntity(
            mediaId = id,
            posterPath = poster_path?: "No picture",
            category = category,
            type = type,
            title = name,
            score = vote_average,
            releaseDate = first_air_date?: "No date",
            overview = overview?: "No overview",
            backdropPath = backdrop_path?: "No picture"
        )
    }

    fun toMediaResult(type: String): MediaResult {
        return MediaResult(
            id = id,
            posterPath = poster_path?: "No picture",
            type = type,
            score = vote_average,
            overview = overview?: "No overview",
            releaseDate = first_air_date?: "No data",
            title = name,
            backdropPath = backdrop_path?: "No picture"
        )
    }


}