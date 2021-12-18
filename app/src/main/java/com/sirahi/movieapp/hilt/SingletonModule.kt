package com.sirahi.movieapp.hilt

import android.content.Context
import androidx.room.Room
import com.sirahi.movieapp.data.local.LocalDatabase
import com.sirahi.movieapp.data.local.dao.*
import com.sirahi.movieapp.data.remote.ApiService
import com.sirahi.movieapp.data.remote.util.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): LocalDatabase =
        Room.databaseBuilder(
            appContext,
            LocalDatabase::class.java,
            "movie_database"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideRetrofitInstance(): ApiService = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    fun providesActorDao(localDatabase: LocalDatabase):ActorDao = localDatabase.actorDao()

    @Provides
    fun providesActorMovieCreditsDao(localDatabase: LocalDatabase):ActorMovieCreditsDao = localDatabase.actorMovieCreditsDao()

    @Provides
    fun providesActorTvCreditsDao(localDatabase: LocalDatabase):ActorTvCreditsDao = localDatabase.actorTvCreditsDao()

    @Provides
    fun providesCastDao(localDatabase: LocalDatabase):CastDao = localDatabase.castDao()

    @Provides
    fun providesMediaResultDao(localDatabase: LocalDatabase):MediaResultDao = localDatabase.mediaResultDao()

    @Provides
    fun providesMovieDetailsDao(localDatabase: LocalDatabase):MovieDetailsDao = localDatabase.movieDetailsDao()

    @Provides
    fun providesTvDetailsDao(localDatabase: LocalDatabase):TvDetailsDao = localDatabase.tvDetailsDao()
}
