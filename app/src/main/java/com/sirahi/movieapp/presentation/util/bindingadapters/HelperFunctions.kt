package com.sirahi.movieapp.presentation.util.bindingadapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.data.remote.util.ApiConstants




fun setPhoto(view: ImageView, url:String){
    Glide.with(view.context)
        .load(ApiConstants.URL_START + url)
        .apply(RequestOptions.centerCropTransform())
        .into(view)
}

