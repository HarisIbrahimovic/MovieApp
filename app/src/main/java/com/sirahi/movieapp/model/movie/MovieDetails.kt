package com.sirahi.movieapp.model.movie

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class MovieDetails(
    val releaseDate: String = "",
    var _voteAverage: Double = 0.0,
    var _title: String = "",
    val runtime: Int = 0,
    var _posterPath: String = "",
    var _overview: String = "",
    var _backdropPath: String = ""
) : BaseObservable() {

    var title: String
        @Bindable get() = _title
        set(value) {
            _title = value
            notifyPropertyChanged(BR.title)
        }

    var overview: String
        @Bindable get() = _overview
        set(value) {
            _overview = value
            notifyPropertyChanged(BR.overview)
        }

    var posterPath: String
        @Bindable get() = _posterPath
        set(value) {
            _posterPath = value
            notifyPropertyChanged(BR.posterPath)
        }

    var backdropPath: String
        @Bindable get() = _backdropPath
        set(value) {
            _backdropPath = value
            notifyPropertyChanged(BR.backdropPath)
        }

    var voteAverage: Double
        @Bindable get() = _voteAverage
        set(value) {
            _voteAverage = value
            notifyPropertyChanged(BR.voteAverage)
        }


    fun setValues(mDetails: MovieDetails) {
        title = mDetails.title
        overview = mDetails.overview
        posterPath = mDetails.posterPath
        backdropPath = mDetails.backdropPath
        voteAverage = mDetails.voteAverage
    }
}