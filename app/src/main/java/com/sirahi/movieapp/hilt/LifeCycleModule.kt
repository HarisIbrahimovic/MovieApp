package com.sirahi.movieapp.hilt

import com.google.firebase.auth.FirebaseAuth
import com.sirahi.movieapp.data.local.dao.*
import com.sirahi.movieapp.data.remote.ApiService
import com.sirahi.movieapp.presentation.usecases.GetPopularMoviesUseCase
import com.sirahi.movieapp.repository.*
import com.sirahi.movieapp.repository.default.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LifeCycleModule {

    @Provides
    fun provideGetPopularMoviesUseCase(repository: MenuRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(repository)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideSignUpRepository(firebaseAuth: FirebaseAuth): SignUpRepository =
        DefaultSignUpRepository(firebaseAuth)

    @Provides
    fun provideRatingRepository(firebaseAuth: FirebaseAuth): RatingRepository =
        DefaultRatingRepository(firebaseAuth)


    @Provides
    fun provideMenuRepository(mediaDao: MediaResultDao, apiService: ApiService): MenuRepository =
        DefaultMenuRepository(mediaDao, apiService)

    @Provides
    fun provideDetailsActorRepository(
        apiService: ApiService,
        actorDao: ActorDao,
        actorMovieCreditsDao: ActorMovieCreditsDao
    ): DetailsActorRepository =
        DefaultDetailsActorRepository(apiService, actorDao, actorMovieCreditsDao)

    @Provides
    fun provideTvDetailsRepository(
        apiService: ApiService,
        tvDetailsDao: TvDetailsDao,
        castDao: CastDao,
    ): DetailsTvRepository =
        DefaultTvDetailsRepository(apiService, tvDetailsDao, castDao)


    @Provides
    fun provideDetailsMovieRepository(
        movieDetailsDao: MovieDetailsDao,
        castDao: CastDao,
        apiService: ApiService
    ): DetailMovieRepository = DefaultMovieDetailsRepository(movieDetailsDao, castDao, apiService)
}