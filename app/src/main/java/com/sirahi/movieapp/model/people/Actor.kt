package com.sirahi.movieapp.model.people

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlin.properties.Delegates

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

    @get:Bindable
    var name: String by Delegates.observable(_name){ _, _, _->notifyPropertyChanged(BR.name)}

    @get:Bindable
    var birthday: String by Delegates.observable(_birthday){ _, _, _->notifyPropertyChanged(BR.name)}

    @get:Bindable
    var biography: String by Delegates.observable(_biography){ _, _, _->notifyPropertyChanged(BR.name)}

    @get:Bindable
    var profilePath: String by Delegates.observable(_profilePath){ _, _, _->notifyPropertyChanged(BR.name)}

}