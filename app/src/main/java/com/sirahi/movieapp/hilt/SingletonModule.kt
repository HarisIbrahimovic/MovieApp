package com.sirahi.movieapp.hilt

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.sirahi.movieapp.data.local.LocalDatabase
import com.sirahi.movieapp.data.local.dao.*
import com.sirahi.movieapp.data.remote.ApiService
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.repository.*
import com.sirahi.movieapp.repository.default.*
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


    @Singleton
    @Provides
    fun provideRetrofitInstance(): ApiService = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesActorDao(localDatabase: LocalDatabase): ActorDao = localDatabase.actorDao()

    @Singleton
    @Provides
    fun providesActorMovieCreditsDao(localDatabase: LocalDatabase): ActorMovieCreditsDao =
        localDatabase.actorMovieCreditsDao()

    @Singleton
    @Provides
    fun providesCastDao(localDatabase: LocalDatabase): CastDao = localDatabase.castDao()

    @Singleton
    @Provides
    fun providesMediaResultDao(localDatabase: LocalDatabase): MediaResultDao =
        localDatabase.mediaResultDao()

    @Singleton
    @Provides
    fun providesMovieDetailsDao(localDatabase: LocalDatabase): MovieDetailsDao =
        localDatabase.movieDetailsDao()

    @Singleton
    @Provides
    fun providesTvDetailsDao(localDatabase: LocalDatabase): TvDetailsDao =
        localDatabase.tvDetailsDao()

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideSignUpRepository(firebaseAuth: FirebaseAuth): SignUpRepository =
        DefaultSignUpRepository(firebaseAuth)

    @Singleton
    @Provides
    fun provideRatingRepository(firebaseAuth: FirebaseAuth): RatingRepository =
        DefaultRatingRepository(firebaseAuth)

    @Singleton
    @Provides
    fun provideMenuRepository(mediaDao: MediaResultDao, apiService: ApiService): MenuRepository =
        DefaultMenuRepository(mediaDao, apiService)

    @Singleton
    @Provides
    fun provideDetailsActorRepository(
        apiService: ApiService,
        actorDao: ActorDao,
        actorMovieCreditsDao: ActorMovieCreditsDao
    ): DetailsActorRepository =
        DefaultDetailsActorRepository(apiService, actorDao, actorMovieCreditsDao)

    @Singleton
    @Provides
    fun provideTvDetailsRepository(
        apiService: ApiService,
        tvDetailsDao: TvDetailsDao,
        castDao: CastDao,
    ): DetailsTvRepository =
        DefaultTvDetailsRepository(apiService, tvDetailsDao, castDao)


    @Singleton
    @Provides
    fun provideDetailsMovieRepository(
        movieDetailsDao: MovieDetailsDao,
        castDao: CastDao,
        apiService: ApiService
    ): DetailMovieRepository = DefaultMovieDetailsRepository(movieDetailsDao, castDao, apiService)
}
