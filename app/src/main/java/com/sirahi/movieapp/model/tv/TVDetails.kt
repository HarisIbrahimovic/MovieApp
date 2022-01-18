package com.sirahi.movieapp.model.tv

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sirahi.movieapp.BR

data class TVDetails(
    var _name: String = "",
    var _overview: String = "",
    var _voteAverage: Double = 0.0,
    var _posterPath: String = "",
    var _backdropPath: String = ""
) : BaseObservable() {

    fun setValues(tvDetails: TVDetails) {
        name = tvDetails.name
        overview = tvDetails.overview
        voteAverage = tvDetails.voteAverage
        posterPath= tvDetails.posterPath
    }

    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
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

}