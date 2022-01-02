package com.sirahi.movieapp.presentation.util

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorDetails
import com.sirahi.movieapp.view.adapters.ActorMovieCreditsAdapter


@BindingAdapter("showActorName")
fun showActorName(view:TextView,incomingActorDetails: IncomingActorDetails?){
    if(checkActorDataSuccess(incomingActorDetails))
        view.text = incomingActorDetails?.data?.name
    if(incomingActorDetails is IncomingActorDetails.Failure)
        Toast.makeText(view.context,incomingActorDetails.error,Toast.LENGTH_SHORT).show()
}

@BindingAdapter("showActorBiography")
fun showActorBiography(view:TextView,incomingActorDetails: IncomingActorDetails?){
    if(checkActorDataSuccess(incomingActorDetails))view.text = incomingActorDetails?.data?.biography
}

@BindingAdapter("showActorBirthDay")
fun showActorBirthDay(view:TextView,incomingActorDetails: IncomingActorDetails?){
    if(checkActorDataSuccess(incomingActorDetails))view.text=incomingActorDetails?.data?.birthday
}

@BindingAdapter("setActorMovieCreditsAdapter")
fun setActorMovieCreditsAdapter(view:RecyclerView, adapter:ActorMovieCreditsAdapter){
    view.adapter = adapter
}

@BindingAdapter("showActorImage")
fun showActorImage(view: ImageView, incomingActorDetails: IncomingActorDetails?){
    if(checkActorDataSuccess(incomingActorDetails)){
        Glide.with(view.context)
                .load(ApiConstants.URL_START+incomingActorDetails?.data?.profilePath)
                .apply(RequestOptions.centerCropTransform())
                .into(view)
    }
}

fun checkActorDataSuccess(incomingActorDetails: IncomingActorDetails?):Boolean{
    return when(incomingActorDetails){
        is IncomingActorDetails.Success->true
        is IncomingActorDetails.Failure -> incomingActorDetails.data!=null
        else -> false
    }
}



