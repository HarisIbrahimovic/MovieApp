package com.sirahi.movieapp.model.movie

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlin.properties.Delegates

data class MovieDetails(
    val releaseDate: String = "",
    var _voteAverage: Double = 0.0,
    var _title: String = "",
    val runtime: Int = 0,
    var _posterPath: String = "",
    var _overview: String = "",
    var _backdropPath: String = ""
) : BaseObservable() {

    @get:Bindable
    var title: String by Delegates.observable(_title){ _, _, _->notifyPropertyChanged(BR.title) }

    @get:Bindable
    var overview: String by Delegates.observable(_overview){ _, _, _->notifyPropertyChanged(BR.overview) }

    @get:Bindable
    var posterPath: String by Delegates.observable(_posterPath){ _, _, _->notifyPropertyChanged(BR.posterPath)}

    @get:Bindable
    var backdropPath: String by Delegates.observable(_backdropPath){ _, _, _->notifyPropertyChanged(BR.backdropPath)}

    @get:Bindable
    var voteAverage: Double by Delegates.observable(_voteAverage){ _, _, _->notifyPropertyChanged(BR.voteAverage)}

    fun setValues(mDetails: MovieDetails) {
        title = mDetails.title
        overview = mDetails.overview
        posterPath = mDetails.posterPath
        backdropPath = mDetails.backdropPath
        voteAverage = mDetails.voteAverage
    }
}