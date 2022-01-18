package com.sirahi.movieapp.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class LoadingUIState(var _state: Boolean) : BaseObservable() {

    var state:Boolean
    @Bindable get() =_state
        set(value) {
            _state = value
            notifyPropertyChanged(BR.state)
        }


}