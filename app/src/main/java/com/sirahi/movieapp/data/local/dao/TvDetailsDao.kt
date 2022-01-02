package com.sirahi.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sirahi.movieapp.data.local.entity.tv.TVDetailsEntity

@Dao
interface TvDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVDetails(tvDetailsEntity: TVDetailsEntity)

    @Query("SELECT * FROM tv_details_table WHERE id =:inputId")
    suspend fun getTvDetails(inputId:Int):TVDetailsEntity

    @Query("DELETE FROM tv_details_table WHERE id =:inputId")
    suspend fun deleteTvDetails(inputId: Int)
}