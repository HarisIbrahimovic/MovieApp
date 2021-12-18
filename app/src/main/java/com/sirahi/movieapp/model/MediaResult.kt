package com.sirahi.movieapp.model

data class MediaResult (
        val id : Int,
        val posterPath : String,
        val type : String,
        val title : String,
        val score:Double,
        val overview:String,
        val releaseDate:String,
        val backdropPath: String
)