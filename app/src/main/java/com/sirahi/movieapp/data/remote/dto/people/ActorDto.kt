package com.sirahi.movieapp.data.remote.dto.people

import com.sirahi.movieapp.data.local.entity.people.ActorEntity

data class ActorDto(
    val adult: Boolean,
    val also_known_as: List<String>?,
    val biography: String,
    val birthday: String,
    val deathday: String?,
    val gender: Int,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val known_for_department: String?,
    val name: String,
    val place_of_birth: String,
    val popularity: Double,
    val profile_path: String
){
    fun toActorEntity():ActorEntity{
        return ActorEntity(
            id = id,
            name = name,
            biography = biography,
            birthday = birthday,
            deathday = deathday,
            profilePath = profile_path,
            placeOfBirth = place_of_birth
        )
    }

}