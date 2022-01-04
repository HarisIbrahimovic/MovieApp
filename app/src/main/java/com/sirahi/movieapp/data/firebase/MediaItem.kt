package com.sirahi.movieapp.data.firebase

import com.sirahi.movieapp.model.MediaResult

data class MediaItem(
        val id:Int=0,
        val title:String="",
        val imagePath:String="",
        val score:Double=0.0
){


        fun toMediaResult(): MediaResult {
                return MediaResult(
                        id=id,
                        title=title,
                        posterPath = imagePath,
                        backdropPath = imagePath,
                        score = score,
                        overview = title,
                        releaseDate = title,
                        type = title
                )
        }

}