package com.sirahi.movieapp.data.local.entity.tv

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sirahi.movieapp.model.tv.TVDetails

@Entity(tableName = "tv_details_table")
data class TVDetailsEntity(
    @PrimaryKey
    val id: Int,
    val inProduction: Boolean,
    val firstAirDate: String,
    val lastAirDate: String,
    val name: String,
    val nextEpisodeToAir: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val overview: String,
    val status: String,
    val type: String,
    val voteAverage: Double,
    val posterPath: String
) {
    fun toTVDetails():TVDetails{
        return TVDetails(
            _posterPath = posterPath,
            _name = name,
            _voteAverage = voteAverage,
            _overview = overview
        )
    }
}