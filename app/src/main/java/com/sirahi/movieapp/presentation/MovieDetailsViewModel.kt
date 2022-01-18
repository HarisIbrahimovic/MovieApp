package com.sirahi.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.model.movie.MovieDetails
import com.sirahi.movieapp.presentation.usecases.details.AddWatchlistToListUseCase
import com.sirahi.movieapp.presentation.usecases.details.GetMovieCastUseCase
import com.sirahi.movieapp.presentation.usecases.details.GetMovieDetailsUseCase
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaCast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addWatchlistToListUseCase: AddWatchlistToListUseCase
) : ViewModel() {

    var movieCastObservable= IncomingMediaCast()
    var mDetails: MovieDetails = MovieDetails()
    var movieId: Int = -1

    fun init(id: Int) {
        if (movieId==-1) {
            setMovieDetails(id)
            setMovieCast(id)
            movieId = id
        }
    }

    private fun setMovieDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        getMovieDetailsUseCase.invoke(id)?.let { mDetails.setValues(it) }
    }

    private fun setMovieCast(id: Int)=viewModelScope.launch(Dispatchers.IO) {
        movieCastObservable.setValues(getMovieCastUseCase.invoke(id))
    }

    fun addMovieToFavorites() {
        val movie = MediaItem(movieId, mDetails.title, mDetails.posterPath, mDetails.voteAverage,"movie")
        addWatchlistToListUseCase.addToFavorites(movie)
    }

    fun addMovieToWatchlist() {
        val movie = MediaItem(movieId, mDetails.title, mDetails.posterPath, mDetails.voteAverage,"movie")
        addWatchlistToListUseCase.addToWatchlist(movie)
    }

}