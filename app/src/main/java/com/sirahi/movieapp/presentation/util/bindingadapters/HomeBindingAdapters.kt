package com.sirahi.movieapp.presentation.util.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.view.adapters.GenreAdapter
import com.sirahi.movieapp.view.adapters.MovieResultAdapter
import com.sirahi.movieapp.view.adapters.TvResultAdapter
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter
import kotlinx.coroutines.flow.StateFlow

fun checkMediaDataSuccess(incomingMediaData: IncomingMediaData?): Boolean {
    return when (incomingMediaData) {
        is IncomingMediaData.Success -> true
        is IncomingMediaData.Failure -> incomingMediaData.data != null
        else -> false
    }
}

@BindingAdapter("setTitleNowPlaying")
fun setTitleNowPlaying(view: TextView, incomingMediaData: IncomingMediaData?) {
    if (checkMediaDataSuccess(incomingMediaData)) if (incomingMediaData != null) {
        view.text = incomingMediaData.data!![0].title
    }
}

@BindingAdapter("setImageNowPlaying")
fun setImageNowPlaying(view: ImageView, incomingMediaData: IncomingMediaData?) {
    if (checkMediaDataSuccess(incomingMediaData)) if (incomingMediaData != null) {
        Glide.with(view.context)
            .load(ApiConstants.URL_START + incomingMediaData.data!![0].posterPath)
            .apply(RequestOptions.centerCropTransform())
            .into(view)
    }
}

@BindingAdapter("setBackDropNowPlaying")
fun setBackDropNowPlaying(view: ImageView, incomingMediaData: IncomingMediaData?) {
    if (checkMediaDataSuccess(incomingMediaData)) if (incomingMediaData != null) {
        Glide.with(view.context)
            .load(ApiConstants.URL_START + incomingMediaData.data!![0].backdropPath)
            .apply(RequestOptions.centerCropTransform())
            .into(view)
    }
}


@BindingAdapter("setMovieResultAdapter")
fun setMovieResultAdapter(view: RecyclerView, movieResultAdapter: MovieResultAdapter) {
    view.adapter = movieResultAdapter
}

@BindingAdapter("setGenreAdapter")
fun setGenreAdapter(view: RecyclerView, genreAdapter: GenreAdapter) {
    view.adapter = genreAdapter
}

@BindingAdapter("setTvResultAdapter")
fun setTvResultAdapter(view: RecyclerView, tvAdapter: TvResultAdapter) {
    view.adapter = tvAdapter
}

@BindingAdapter("setDiscoverdapter")
fun setGenreAdapter(view: RecyclerView, discoverAdapter: VerticalMediaAdapter) {
    view.adapter = discoverAdapter
}

@BindingAdapter("checkLoadingMediaData")
fun checkLoadingMediaData(view: View, incomingMediaData: IncomingMediaData?) {
    view.visibility = when (incomingMediaData) {
        is IncomingMediaData.Loading -> View.VISIBLE
        else -> View.GONE
    }
}


@BindingAdapter("checkLoadingMediaData")
fun checkLoadingMediaData(view: View, incomingMediaData: StateFlow<IncomingMediaData>) {
    view.visibility = when (incomingMediaData.value) {
        is IncomingMediaData.Loading -> View.VISIBLE
        else -> View.GONE
    }
}




