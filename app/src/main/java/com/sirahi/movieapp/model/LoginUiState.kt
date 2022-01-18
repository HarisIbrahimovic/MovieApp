package com.sirahi.movieapp.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class LoginUiState(
    var _username: String = "",
    var _email: String = "",
    var _password: String = "",
) : BaseObservable() {


    var username: String
        @Bindable get() = _username
        set(value) {
            _username = value
            notifyPropertyChanged(BR.username)
        }

    var email: String
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }


    var password: String
        @Bindable get() = _password
        set(value) {
            _password = value
            notifyPropertyChanged(BR.password)
        }

    fun setValues() {
        username = ""
        email = ""
        password = ""
    }


}