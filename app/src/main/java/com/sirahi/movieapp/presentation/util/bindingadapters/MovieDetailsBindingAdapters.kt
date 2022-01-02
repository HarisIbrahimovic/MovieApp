package com.sirahi.movieapp.presentation.util

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.view.adapters.ActorMovieCreditsAdapter
import com.sirahi.movieapp.view.adapters.MovieCastAdapter


fun checkMovieDataSuccess(incomingMovieDetails: IncomingMovieDetails?):Boolean{
    return when(incomingMovieDetails){
        is IncomingMovieDetails.Success->true
        is IncomingMovieDetails.Failure -> incomingMovieDetails.data != null
        else -> false
    }
}


@BindingAdapter("showTitle")
fun showTitle(view: TextView, incomingMovieDetails: IncomingMovieDetails?){
    if(checkMovieDataSuccess(incomingMovieDetails)) view.text = incomingMovieDetails?.data?.title

}

@BindingAdapter("showOverview")
fun showOverview(view: TextView, incomingMovieDetails: IncomingMovieDetails?){
    if(checkMovieDataSuccess(incomingMovieDetails))
        view.text = incomingMovieDetails?.data?.overview
    if(incomingMovieDetails is IncomingMovieDetails.Failure)
        Toast.makeText(view.context,incomingMovieDetails.error,Toast.LENGTH_SHORT).show()
}

@BindingAdapter("showBackdropImage")
fun showBackdropImage(view: ImageView, incomingMovieDetails: IncomingMovieDetails?){
    if(checkMovieDataSuccess(incomingMovieDetails)){
        Glide.with(view.context)
                .load(ApiConstants.URL_START+incomingMovieDetails?.data?.backdropPath)
                .apply(RequestOptions.centerCropTransform())
                .into(view)
    }
}

@BindingAdapter("showPosterImage")
fun showPosterImage(view: ImageView, incomingMovieDetails: IncomingMovieDetails?){
    if(checkMovieDataSuccess(incomingMovieDetails)){
        Glide.with(view.context)
                .load(ApiConstants.URL_START+incomingMovieDetails?.data?.posterPath)
                .apply(RequestOptions.centerCropTransform())
                .into(view)
    }
}

@BindingAdapter("setCastAdapter")
fun setCastAdapter(view: RecyclerView, adapter: MovieCastAdapter){
    view.adapter = adapter
}