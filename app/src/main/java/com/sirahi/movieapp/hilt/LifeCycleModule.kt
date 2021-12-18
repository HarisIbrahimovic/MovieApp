package com.sirahi.movieapp.hilt

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.sirahi.movieapp.data.local.dao.CastDao
import com.sirahi.movieapp.data.local.dao.MediaResultDao
import com.sirahi.movieapp.data.local.dao.MovieDetailsDao
import com.sirahi.movieapp.data.remote.ApiService
import com.sirahi.movieapp.repository.DetailMovieRepository
import com.sirahi.movieapp.repository.MenuRepository
import com.sirahi.movieapp.repository.SignUpRepository
import com.sirahi.movieapp.repository.default.DefaultMenuRepository
import com.sirahi.movieapp.repository.default.DefaultMovieDetailsRepository
import com.sirahi.movieapp.repository.default.DefaultSignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object LifeCycleModule {

    @Provides
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideSignUpRepository(firebaseAuth: FirebaseAuth):SignUpRepository = DefaultSignUpRepository(firebaseAuth)

    @Provides
    fun provideMenuRepository(mediaDao: MediaResultDao, apiService: ApiService):MenuRepository = DefaultMenuRepository(mediaDao, apiService)

    @Provides
    fun provideDetailsMovieRepository(movieDetailsDao: MovieDetailsDao,castDao: CastDao,apiService: ApiService): DetailMovieRepository = DefaultMovieDetailsRepository(movieDetailsDao, castDao, apiService)
}