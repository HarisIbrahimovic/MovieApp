package com.sirahi.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sirahi.movieapp.model.MediaResult

@Entity(tableName = "media_result_table")
data class MediaResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val mediaId : Int,
    val score: Double,
    val posterPath : String,
    val category : String,
    val type : String,
    val title : String,
    val overview:String,
    val releaseDate:String
){
    fun toMediaResult() : MediaResult {
        return MediaResult(
            id = mediaId,
            posterPath = posterPath,
            type = type,
            title = title,
            score=score,
            overview=overview,
            releaseDate= releaseDate
        )
    }


}
