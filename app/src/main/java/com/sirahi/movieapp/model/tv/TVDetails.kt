package com.sirahi.movieapp.model.tv

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sirahi.movieapp.BR
import kotlin.properties.Delegates

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

    @get:Bindable
    var name: String by Delegates.observable(_name){ _, _, _->notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.name) }

    @get:Bindable
    var overview: String by Delegates.observable(_overview){ _, _, _->notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.overview) }

    @get:Bindable
    var posterPath: String by Delegates.observable(_posterPath){ _, _, _->notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.posterPath)}

    @get:Bindable
    var backdropPath: String by Delegates.observable(_backdropPath){ _, _, _->notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.backdropPath)}

    @get:Bindable
    var voteAverage: Double by Delegates.observable(_voteAverage){ _, _, _->notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.voteAverage)}

}