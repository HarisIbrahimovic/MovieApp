package com.sirahi.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sirahi.movieapp.data.local.entity.people.ActorMovieCreditsEntity


@Dao
interface ActorMovieCreditsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCredits(actorMovieCredits: List<ActorMovieCreditsEntity>)

    @Query("SELECT * FROM actor_movie_credits_table WHERE actorId = :inputId")
    suspend fun getActorCredits(inputId:Int):List<ActorMovieCreditsEntity>

}