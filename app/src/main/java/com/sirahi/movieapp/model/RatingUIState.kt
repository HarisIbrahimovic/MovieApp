package com.sirahi.movieapp.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlin.properties.Delegates

class RatingUIState:BaseObservable() {

    @get:Bindable
    var comment by Delegates.observable(""){ _, _, _->notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.comment)}


    @get:Bindable
    var value by Delegates.observable(0.0F){ _, _, _->notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.value)}

}