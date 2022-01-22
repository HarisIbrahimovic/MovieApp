package com.sirahi.movieapp.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlin.properties.Delegates

data class LoadingUIState(var _state: Boolean) : BaseObservable() {

    @get:Bindable
    var state:Boolean by Delegates.observable(false){_,_,_->notifyPropertyChanged(BR.state)}


}