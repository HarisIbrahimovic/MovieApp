package com.sirahi.movieapp.hilt

import com.sirahi.movieapp.presentation.usecases.*
import com.sirahi.movieapp.presentation.usecases.details.*
import com.sirahi.movieapp.presentation.usecases.details.actor.GetActorDetailsUseCase
import com.sirahi.movieapp.presentation.usecases.details.actor.GetActorMovieCreditsUseCase
import com.sirahi.movieapp.presentation.usecases.details.movies.GetMovieDetailsUseCase
import com.sirahi.movieapp.presentation.usecases.details.tv.GetTvDetailsUseCase
import com.sirahi.movieapp.presentation.usecases.ratings.AddRatingUseCase
import com.sirahi.movieapp.presentation.usecases.signup.SignUpUseCase
import com.sirahi.movieapp.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LifeCycleModule {

    @Provides
    fun provideGetMoviesByCategory(repository: MenuRepository): GetMoviesByCategoryUseCase =
        GetMoviesByCategoryUseCase(repository)

    @Provides
    fun provideGetTvByCategory(repository: MenuRepository): GetTvByCategoryUseCase =
        GetTvByCategoryUseCase(repository)

    @Provides
    fun provideDiscoverUseCase(repository: MenuRepository): DiscoverMoviesUseCase =
        DiscoverMoviesUseCase(repository)

    @Provides
    fun provideSearchUseCase(repository: MenuRepository): SearchUseCase =
        SearchUseCase(repository)

    @Provides
    fun provideGenreUseCase(repository: MenuRepository): GetGenresUseCase =
        GetGenresUseCase(repository)

    @Provides
    fun provideUserListUseCase(repository: MenuRepository): UserListsUseCase =
        UserListsUseCase(repository)

    @Provides
    fun provideGetUserNameUseCase(repository: MenuRepository): GetUserNameUseCase =
        GetUserNameUseCase(repository)

    @Provides
    fun provideSignUpUseCase(repository: SignUpRepository): SignUpUseCase =
        SignUpUseCase(repository)

    @Provides
    fun provideMovieDetailsUseCase(repository: DetailMovieRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(repository)

    @Provides
    fun provideMovieCastUseCase(repository: DetailMovieRepository): GetMovieCastUseCase =
        GetMovieCastUseCase(repository)


    @Provides
    fun provideAddToWatchlistUseCase(repository: DetailMovieRepository): AddWatchlistToListUseCase =
        AddWatchlistToListUseCase(repository)

    @Provides
    fun provideGetActorDetailsUseCase(repository: DetailsActorRepository): GetActorDetailsUseCase =
        GetActorDetailsUseCase(repository)

    @Provides
    fun provideGetActorMovieCreditsUseCase(repository: DetailsActorRepository): GetActorMovieCreditsUseCase =
        GetActorMovieCreditsUseCase(repository)

    @Provides
    fun provideGetTvDetailsUseCase(repository: DetailsTvRepository):GetTvDetailsUseCase=
        GetTvDetailsUseCase(repository)

    @Provides
    fun provideRatingUseCase(repository:RatingRepository):AddRatingUseCase=
        AddRatingUseCase(repository)


}