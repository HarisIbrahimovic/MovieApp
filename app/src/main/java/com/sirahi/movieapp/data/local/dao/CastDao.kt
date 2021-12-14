package com.sirahi.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sirahi.movieapp.data.local.entity.CastEntity

@Dao
interface CastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCast(list: List<CastEntity>)

    @Query("DELETE FROM cast_table WHERE movieId = :id")
    suspend fun deleteCast(id:Int)

    @Query("SELECT * FROM cast_table WHERE movieId = :id")
    suspend fun getCast(id:Int):List<CastEntity>

}