package com.sirahi.movieapp.model.people

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class Actor(
    var _name: String="",
    var _biography: String="",
    var _birthday: String="",
    var _profilePath: String="",
) : BaseObservable() {

    fun setValues(actor: Actor) {
        name= actor.name
        biography= actor.biography
        profilePath= actor.profilePath
        birthday= actor.birthday
    }


    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    var birthday: String
        @Bindable get() = _birthday
        set(value) {
            _birthday = value
            notifyPropertyChanged(BR.birthday)
        }

    var biography: String
        @Bindable get() = _biography
        set(value) {
            _biography = value
            notifyPropertyChanged(BR.biography)
        }

    var profilePath: String
        @Bindable get() = _profilePath
        set(value) {
            _profilePath = value
            notifyPropertyChanged(BR.profilePath)
        }


}