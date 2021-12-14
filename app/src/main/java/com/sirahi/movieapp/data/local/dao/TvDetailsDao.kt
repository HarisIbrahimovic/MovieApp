package com.sirahi.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sirahi.movieapp.data.local.entity.tv.TVDetailsEntity

@Dao
interface TvDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVDetails(tvDetailsEntity: TVDetailsEntity)
}