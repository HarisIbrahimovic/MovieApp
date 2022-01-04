package com.sirahi.movieapp.data.remote.dto.tv

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
    var next_episode_to_air: String? = "",
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
            in_production!!,
            first_air_date!!,
            last_air_date!!,
            name!!,
            next_episode_to_air!!,
            number_of_episodes!!,
            number_of_seasons!!,
            overview!!,
            status!!,
            type!!,
            vote_average!!,
            poster_path!!
        )
    }

    fun checkNull() {
        if (in_production == null) in_production = false
        if (first_air_date == null) first_air_date = ""
        if (last_air_date == null) last_air_date = ""
        if (name == null) name = ""
        if (next_episode_to_air == null) next_episode_to_air = ""
        if (number_of_episodes == null) number_of_episodes = 0
        if (number_of_seasons == null) number_of_seasons = 0
        if (overview == null) overview = ""
        if (status == null) status = ""
        if (type == null) type = ""
        if (vote_average == null) vote_average = 0.0
        if (poster_path == null) poster_path = ""
    }

}