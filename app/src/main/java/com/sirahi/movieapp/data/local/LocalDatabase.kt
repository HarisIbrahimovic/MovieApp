package com.sirahi.movieapp.data.local

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sirahi.movieapp.data.local.dao.*
import com.sirahi.movieapp.data.local.entity.CastEntity
import com.sirahi.movieapp.data.local.entity.MediaResultEntity
import com.sirahi.movieapp.data.local.entity.movie.MovieDetailsEntity
import com.sirahi.movieapp.data.local.entity.people.ActorEntity
import com.sirahi.movieapp.data.local.entity.people.ActorMovieCreditsEntity
import com.sirahi.movieapp.data.local.entity.people.ActorTVCreditsEntity
import com.sirahi.movieapp.data.local.entity.tv.TVDetailsEntity


@Database(
        entities = [MovieDetailsEntity::class, ActorMovieCreditsEntity::class, ActorEntity::class, ActorTVCreditsEntity::class, TVDetailsEntity::class, CastEntity::class, MediaResultEntity::class],
        version = 1,
        exportSchema = false
)
abstract class LocalDatabase :RoomDatabase(){
    abstract fun actorDao(): ActorDao
    abstract fun actorMovieCreditsDao(): ActorMovieCreditsDao
    abstract fun actorTvCreditsDao(): ActorTvCreditsDao
    abstract fun castDao(): CastDao
    abstract fun mediaResultDao(): MediaResultDao
    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun tvDetailsDao(): TvDetailsDao
}