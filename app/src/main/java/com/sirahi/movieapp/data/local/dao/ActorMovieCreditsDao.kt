package com.sirahi.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sirahi.movieapp.data.local.entity.people.ActorMovieCreditsEntity


@Dao
interface ActorMovieCreditsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCredits(actorMovieCredits: ActorMovieCreditsEntity)
}