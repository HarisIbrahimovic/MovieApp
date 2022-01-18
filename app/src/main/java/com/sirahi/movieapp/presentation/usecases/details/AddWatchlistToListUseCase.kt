package com.sirahi.movieapp.presentation.usecases.details

import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.repository.DetailMovieRepository
import javax.inject.Inject

class AddWatchlistToListUseCase
@Inject
constructor(private val repository: DetailMovieRepository) {

    fun addToWatchlist(movie: MediaItem) {
        repository.addToWatchlist(movie)
    }

    fun addToFavorites(movie: MediaItem) {
        repository.addToFavorites(movie)
    }
}