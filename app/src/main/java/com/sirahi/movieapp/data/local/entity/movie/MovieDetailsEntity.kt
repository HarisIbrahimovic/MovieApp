package com.sirahi.movieapp.data.local.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sirahi.movieapp.model.movie.MovieDetails

@Entity(tableName = "movie_details_table")
data class MovieDetailsEntity (
    @PrimaryKey
    val id:Int,
    val releaseDate: String,
    val voteAverage:Double,
    val title: String,
    val runtime: Int,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    ){
        fun toMovieDetails():MovieDetails{
            return MovieDetails(
                releaseDate, voteAverage, title, runtime, posterPath, overview,backdropPath
            )
        }
}