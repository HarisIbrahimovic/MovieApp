package com.sirahi.movieapp.data.remote.dto.credits

import com.sirahi.movieapp.data.local.entity.CastEntity

data class MovieCastDto(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    var profile_path: String?
){
    fun toMovieCastEntity(movieId: Int) : CastEntity {
        return  CastEntity(
            actorName = name,
            profilePath = profile_path!!,
            characterName = character,
            actorId = id,
            movieId = movieId
        )
    }

    fun checkNull(){
        if(profile_path==null)profile_path="No picutre"
    }
}