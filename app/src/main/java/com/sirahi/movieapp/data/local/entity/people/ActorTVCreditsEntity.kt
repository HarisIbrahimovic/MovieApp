package com.sirahi.movieapp.data.local.entity.people

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sirahi.movieapp.model.tv.TvCast

@Entity(tableName = "actor_tv_credits_table")
data class ActorTVCreditsEntity(
    @PrimaryKey
    val creditId: String,
    val actorId:Int,
    val character: String,
    val episodeCount: Int,
    val firstAirDate: String,
    val id: Int,
    val name: String,
    val posterPath: String,
    val voteAverage: Double,
) {
    fun toTvCast(): TvCast{
        return TvCast(
            character, episodeCount, firstAirDate, id, name, posterPath, voteAverage
        )
    }

}