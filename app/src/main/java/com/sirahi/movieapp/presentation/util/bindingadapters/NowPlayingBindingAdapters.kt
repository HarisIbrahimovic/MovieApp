package com.sirahi.movieapp.presentation.util.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData

@BindingAdapter("setImageNowPlaying")
fun setImageNowPlaying(view: ImageView, incomingMediaData: IncomingMediaData?) {
    if (checkMediaDataSuccess(incomingMediaData))
        incomingMediaData?.data?.get(0)?.posterPath?.let { setPhoto(view, it) }
}

@BindingAdapter("setBackDropNowPlaying")
fun setBackDropNowPlaying(view: ImageView, incomingMediaData: IncomingMediaData?) {
    if (checkMediaDataSuccess(incomingMediaData))
        incomingMediaData?.data?.get(0)?.backdropPath?.let { setPhoto(view, it) }
}