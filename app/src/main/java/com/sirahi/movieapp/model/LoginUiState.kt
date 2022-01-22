package com.sirahi.movieapp.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlin.properties.Delegates

class LoginUiState: BaseObservable() {

    @get:Bindable
    var username by Delegates.observable(""){_,_,_->notifyPropertyChanged(BR.username)}


    @get:Bindable
    var email by Delegates.observable(""){_,_,_->notifyPropertyChanged(BR.email)}


    @get:Bindable
    var password by Delegates.observable(""){_,_,_->notifyPropertyChanged(BR.password)}

    fun setValues() {
        username = ""
        email = ""
        password = ""
    }

}