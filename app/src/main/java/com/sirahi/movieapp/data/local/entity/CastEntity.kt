package com.sirahi.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sirahi.movieapp.model.movie.MovieCast

@Entity(tableName = "cast_table")
data class CastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,
    val actorName : String,
    val characterName : String,
    val actorId: Int,
    val profilePath:String,
    val movieId: Int
) {
    fun toMovieCast(): MovieCast {
        return MovieCast(
            actorName = actorName,
            characterName = characterName,
            actorId = actorId,
            profilePath = profilePath
        )
    }
}