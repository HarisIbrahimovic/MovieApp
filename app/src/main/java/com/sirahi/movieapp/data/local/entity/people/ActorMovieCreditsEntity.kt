package com.sirahi.movieapp.data.local.entity.people

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sirahi.movieapp.model.people.ActorMovieCredits

@Entity(tableName = "actor_movie_credits_table")
data class ActorMovieCreditsEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val movieId:Int,
    val actorId: Int,
    val movieTitle: String,
    val character: String,
    val voteAverage: Double
    ){
        fun toActorMovieCredits() : ActorMovieCredits {
            return ActorMovieCredits(
                movieId, actorId, movieTitle, character, voteAverage
            )
        }
}