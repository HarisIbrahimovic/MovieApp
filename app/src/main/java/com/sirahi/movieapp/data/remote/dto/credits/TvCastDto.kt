package com.sirahi.movieapp.data.remote.dto.credits

import com.sirahi.movieapp.data.local.entity.people.ActorTVCreditsEntity

data class TvCastDto(
    val backdrop_path: String?,
    val character: String?,
    val credit_id: String?,
    val episode_count: Int?,
    val first_air_date: String?,
    val genre_ids: List<Int>?,
    val id: Int,
    val name: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val vote_average: Double?,
    val vote_count: Int?
){

     fun toTvCredits(actorId: Int):ActorTVCreditsEntity{
         return ActorTVCreditsEntity(
             creditId = credit_id?:"No data",
             actorId = actorId,
             character = character?:"No data",
             episodeCount = episode_count?:0,
             firstAirDate = first_air_date?:"No data",
             id = id,
             name = name?:"No data",
             posterPath = poster_path?:"No data",
             voteAverage = vote_average?:0.0
         )
     }

}