package com.sirahi.movieapp.presentation.util.incomingdata

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sirahi.movieapp.model.people.ActorMovieCredits
import kotlin.properties.Delegates

class IncomingActorCredits : BaseObservable() {

    fun setValues(incomingData: IncomingActorCredits) {
        creditList = incomingData.creditList
        errorMessage = incomingData.errorMessage
    }


    @get:Bindable
    var creditList by Delegates.observable(ArrayList<ActorMovieCredits>()) { _, _, _ ->
        notifyPropertyChanged(
            BR.creditList
        )
    }

    @get:Bindable
    var errorMessage by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.creditList) }

}