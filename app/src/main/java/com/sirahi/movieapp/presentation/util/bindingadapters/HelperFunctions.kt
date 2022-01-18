package com.sirahi.movieapp.presentation.util.bindingadapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingTvDetails


fun checkMediaDataSuccess(incomingMediaData: IncomingMediaData?): Boolean {
    return when (incomingMediaData) {
        is IncomingMediaData.Success -> true
        is IncomingMediaData.Failure -> incomingMediaData.data != null
        else -> false
    }
}


fun setPhoto(view: ImageView, url:String){
    Glide.with(view.context)
        .load(ApiConstants.URL_START + url)
        .apply(RequestOptions.centerCropTransform())
        .into(view)
}

