package com.sirahi.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sirahi.movieapp.data.local.entity.movie.MovieDetailsEntity

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetailsEntity: MovieDetailsEntity)

    @Query("SELECT * FROM movie_details_table WHERE id = :movieId")
    suspend fun getMovieData(movieId:Int):MovieDetailsEntity

    @Query("DELETE FROM movie_details_table WHERE id =:id")
    suspend fun deleteMovie(id: Int)
}