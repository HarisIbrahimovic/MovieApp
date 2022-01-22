package com.sirahi.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sirahi.movieapp.data.local.entity.MediaResultEntity
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.Response

@Dao
interface MediaResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaResult(list:List<MediaResultEntity>)

    @Query("SELECT * FROM media_result_table where type = :resultType")
    suspend fun getMediaResult(resultType:String):List<MediaResultEntity>?

    @Query("DELETE FROM media_result_table WHERE type=:cType")
    fun deleteAllPMovies(cType:String="movie")

    @Query("DELETE FROM media_result_table WHERE type=:cType")
    fun deleteAllTV(cType:String="TV")

    @Query("DELETE FROM media_result_table WHERE type=:name")
    fun deleteAllGenre(name: String)


}