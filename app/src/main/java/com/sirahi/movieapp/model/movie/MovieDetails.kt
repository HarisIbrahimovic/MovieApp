package com.sirahi.movieapp.model.movie

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class MovieDetails(
    val releaseDate: String,
    val voteAverage:Double,
    var _title: String,
    val runtime: Int,
    val posterPath: String,
    val overview: String,
    val backdropPath: String
):BaseObservable(){

    var title:String
    @Bindable get() = _title
    set(value) {
        _title=value
        notifyPropertyChanged(BR.title)
    }

}