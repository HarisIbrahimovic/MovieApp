package com.sirahi.movieapp.presentation.usecases.details.tv

import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.model.tv.TVDetails
import com.sirahi.movieapp.repository.DetailsTvRepository
import javax.inject.Inject

class AddToWatchlistTvUseCase@Inject constructor(private val repository: DetailsTvRepository) {

    fun addToFavorites(tvDetailsObservable: TVDetails,id:Int) {
        val item = MediaItem(id,tvDetailsObservable.name,tvDetailsObservable.posterPath,tvDetailsObservable.voteAverage,"tv")
        repository.addToFavorites(item)
    }

    fun addToWatchlist(tvDetailsObservable: TVDetails,id:Int) {
        val item = MediaItem(id,tvDetailsObservable.name,tvDetailsObservable.posterPath,tvDetailsObservable.voteAverage,"tv")
        repository.addToWatchlist(item)
    }

}