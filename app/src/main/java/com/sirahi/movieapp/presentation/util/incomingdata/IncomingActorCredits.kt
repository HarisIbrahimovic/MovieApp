package com.sirahi.movieapp.presentation.util.incomingdata

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sirahi.movieapp.model.people.ActorMovieCredits

data class IncomingActorCredits(
    var _creditList: ArrayList<ActorMovieCredits> = ArrayList(),
    var _errorMessage: String = ""
) : BaseObservable() {

    fun setValues(incomingData: IncomingActorCredits) {
        creditList = incomingData.creditList
        errorMessage = incomingData.errorMessage
    }

    var creditList: ArrayList<ActorMovieCredits>
        @Bindable get() = _creditList
        set(value) {
            _creditList = value
            notifyPropertyChanged(BR.creditList)
        }

    var errorMessage: String
        @Bindable get() = _errorMessage
        set(value) {
            _errorMessage = value
            notifyPropertyChanged(BR.errorMessage)
        }

}