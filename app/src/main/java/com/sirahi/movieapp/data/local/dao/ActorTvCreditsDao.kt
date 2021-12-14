package com.sirahi.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sirahi.movieapp.data.local.entity.people.ActorTVCreditsEntity

@Dao
interface ActorTvCreditsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvCredits(actorTVCreditsEntity: ActorTVCreditsEntity)
}