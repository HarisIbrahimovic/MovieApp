package com.sirahi.movieapp.data.local.entity.people

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sirahi.movieapp.model.people.Actor

@Entity(tableName = "actor_table")
data class ActorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val biography: String,
    val birthday: String,
    val deathday: String?,
    val profilePath: String,
    val placeOfBirth : String,
){
    fun toActor():Actor{
        return Actor(
            name = name,
            biography = biography,
            birthday = birthday,
            deathday = deathday,
            profilePath = profilePath,
            placeOfBirth = placeOfBirth
        )
    }
}
