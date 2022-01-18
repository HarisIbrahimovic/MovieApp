package com.sirahi.movieapp.presentation.util.incomingdata

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sirahi.movieapp.BR
import com.sirahi.movieapp.model.movie.MovieCast

data class IncomingMediaCast(
    var _castList: ArrayList<MovieCast> = ArrayList(),
    var _errorMessage: String = ""
) : BaseObservable() {

    fun setValues(cast: IncomingMediaCast) {
        castList = cast.castList
        errorMessage = cast.errorMessage
    }

    var castList: ArrayList<MovieCast>
        @Bindable get() = _castList
        set(value) {
            _castList = value
            notifyPropertyChanged(BR.castList)
        }

    var errorMessage: String
        @Bindable get() = _errorMessage
        set(value) {
            _errorMessage = value
            notifyPropertyChanged(BR.errorMessage)
        }

}