package com.sirahi.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sirahi.movieapp.data.local.entity.movie.MovieDetailsEntity
import com.sirahi.movieapp.data.local.entity.people.ActorEntity

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActor(actorEntity: ActorEntity)
    

}
