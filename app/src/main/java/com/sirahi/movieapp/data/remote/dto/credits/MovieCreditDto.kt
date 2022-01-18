package com.sirahi.movieapp.data.remote.dto.credits

import com.sirahi.movieapp.data.local.entity.people.ActorMovieCreditsEntity

data class MovieCreditDto(
    val adult: Boolean?,
    val backdrop_path: String?,
    val character: String?,
    val credit_id: String?,
    val genre_ids: List<Int>?,
    val id: Int,
    val order: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    var poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
){

    fun toActorMovieCreditsEntity(actorId:Int):ActorMovieCreditsEntity{
        return ActorMovieCreditsEntity(
             movieId= id,
             actorId = actorId ,
             movieTitle = title?:"No title",
             character = character?:"No character",
             voteAverage = vote_average?:0.0,
             posterPath = poster_path?:"No poster path"
        )
    }


}