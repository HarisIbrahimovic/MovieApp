package com.sirahi.movieapp.data.remote.dto.tv

import com.google.gson.internal.LinkedTreeMap
import com.sirahi.movieapp.data.local.entity.tv.TVDetailsEntity

data class TVDetailsDto(
    val backdrop_path: String,
    val episode_run_time: List<Int>,
    var first_air_date: String? = "",
    val homepage: String,
    val id: Int,
    var in_production: Boolean? = false,
    val languages: List<String>,
    var last_air_date: String? = "",
    var name: String? = "",
    var number_of_episodes: Int? = 0,
    var number_of_seasons: Int? = 0,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    var overview: String? = "",
    val popularity: Double,
    var poster_path: String? = "",
    var status: String? = "",
    val tagline: String? = "",
    var type: String? = "",
    var vote_average: Double? = 0.0,
    val vote_count: Int
) {
    fun toTVDetailsEntity(): TVDetailsEntity {
        return TVDetailsEntity(
            id,
            in_production?:false,
            first_air_date?:"Unknown",
            last_air_date?:"Unknown",
            name?:"No name",
            "Unknown",
            number_of_episodes?:0,
            number_of_seasons?:0,
            overview?:"No overview",
            status?:"no status",
            type?: "no type",
            vote_average?: 0.0,
            poster_path?:"No poster"
        )
    }



}