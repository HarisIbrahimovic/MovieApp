package com.sirahi.movieapp.presentation.util.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.presentation.util.checkMovieDataSuccess
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingTvDetails

fun checkTvDataSucces(incomingTvDetails: IncomingTvDetails?): Boolean {
    return when (incomingTvDetails) {
        is IncomingTvDetails.Success -> true
        is IncomingTvDetails.Failure -> incomingTvDetails.data != null
        else -> false
    }
}

@BindingAdapter("showTvTitle")
fun showTvTitle(view: TextView, incomingTvDetails: IncomingTvDetails?) {
    if (checkTvDataSucces(incomingTvDetails)) view.text = incomingTvDetails?.data?.name
}

@BindingAdapter("showOverviewTv")
fun showOverviewTv(view: TextView, incomingTvDetails: IncomingTvDetails?) {
    if (checkTvDataSucces(incomingTvDetails)) view.text = incomingTvDetails?.data?.overview
}

@BindingAdapter("showPosterImageTv")
fun showPosterImageTV(view: ImageView, incomingTvDetails: IncomingTvDetails?) {
    if (checkTvDataSucces(incomingTvDetails)) {
        Glide.with(view.context)
            .load(ApiConstants.URL_START + incomingTvDetails?.data?.posterPath)
            .apply(RequestOptions.centerCropTransform())
            .into(view)
    }
}



@BindingAdapter("showBackDropTv")
fun showBackDropTv(view: ImageView, incomingTvDetails: IncomingTvDetails?) {
    if (checkTvDataSucces(incomingTvDetails)) {
        Glide.with(view.context)
            .load(ApiConstants.URL_START + incomingTvDetails?.data?.posterPath)
            .apply(RequestOptions.centerCropTransform())
            .into(view)
    }
}

