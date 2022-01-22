package com.sirahi.movieapp.presentation.util.incomingdata

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sirahi.movieapp.model.MediaResult
import kotlin.properties.Delegates

class IncomingMediaDataDelegates:BaseObservable() {

    fun setValues(data: IncomingMediaDataDelegates) {
        mediaList = data.mediaList
        errorMessage = data.errorMessage
    }

    @get:Bindable
    var mediaList by Delegates.observable(ArrayList<MediaResult>()){ _, _, _->notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.mediaList)}


    @get:Bindable
    var errorMessage by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.errorMessage) }


    @get:Bindable
    var loadingState by Delegates.observable(false) { _, _, _ -> notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.loadingState) }

}