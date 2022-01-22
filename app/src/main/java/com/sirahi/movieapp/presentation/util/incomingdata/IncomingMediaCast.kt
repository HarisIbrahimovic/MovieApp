package com.sirahi.movieapp.presentation.util.incomingdata

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sirahi.movieapp.BR
import com.sirahi.movieapp.model.movie.MovieCast
import kotlin.properties.Delegates

class IncomingMediaCast: BaseObservable() {

    fun setValues(cast: IncomingMediaCast) {
        castList = cast.castList
        errorMessage = cast.errorMessage
    }


    @get:Bindable
    var castList by Delegates.observable(ArrayList<MovieCast>()){_,_,_->notifyPropertyChanged(BR.castList)}

    @get:Bindable
    var errorMessage by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.creditList) }
}