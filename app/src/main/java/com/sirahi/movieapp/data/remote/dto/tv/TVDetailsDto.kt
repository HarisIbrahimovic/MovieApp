package com.sirahi.movieapp.data.remote.dto.tv

import com.sirahi.movieapp.data.local.entity.tv.TVDetailsEntity

data class TVDetailsDto(
    val backdrop_path: String,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val homepage: String,
    val id: Int,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String,
    val name: String,
    val next_episode_to_air: String,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val status: String,
    val tagline: String,
    val type: String,
    val vote_average: Int,
    val vote_count: Int
){
    fun toTVDetailsEntity() : TVDetailsEntity{
        return TVDetailsEntity(
            id,
            in_production,
            first_air_date,
            last_air_date,
            name,
            next_episode_to_air,
            number_of_episodes,
            number_of_seasons,
            overview,
            status,
            type,
            vote_average,
            poster_path
        )
    }

}