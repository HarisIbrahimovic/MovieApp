package com.sirahi.movieapp.data.remote.dto.tv

import com.sirahi.movieapp.data.local.entity.MediaResultEntity
import com.sirahi.movieapp.model.MediaResult

data class TVResultDto(
    val backdrop_path: String,
    var first_air_date: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    var overview: String?,
    val popularity: Double,
    var poster_path: String?,
    val vote_average: Double,
    val vote_count: Int
){
    fun toMediaResultEntity(category : String,type:String): MediaResultEntity {
        return MediaResultEntity(
                mediaId = id,
                posterPath = poster_path!!,
                category = category,
                type = type,
                title = name,
                score = vote_average,
                releaseDate = first_air_date!!,
                overview = overview!!
        )
    }

    fun toMediaResult(type: String): MediaResult {
        return MediaResult(
            id=id,
            posterPath= poster_path!!,
            type=type,
            score=vote_average,
            overview=overview!!,
            releaseDate=first_air_date!!,
            title = name
        )
    }

    fun checkNull(){
        if (poster_path==null)poster_path="No photo"
        if (first_air_date==null)first_air_date="No date"
        if (overview==null)overview="No overview"
    }
}