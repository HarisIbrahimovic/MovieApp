package com.sirahi.movieapp.presentation.util.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.view.adapters.MovieCastAdapter


@BindingAdapter("setCastAdapter")
fun setCastAdapter(view: RecyclerView, adapter: MovieCastAdapter) {
    view.adapter = adapter
}

@BindingAdapter("setImageGlide")
fun setImageGlide(view: ImageView, url: String) {
    setPhoto(view, url)
}


@BindingAdapter("setCastList", "setList")
fun setCastList(view:RecyclerView,adapter: MovieCastAdapter, list: ArrayList<MovieCast>) {
    adapter.setList(list)
}

@BindingAdapter("showError")
fun showError(view: View, message: String?) {
    if (!message.isNullOrEmpty())
        Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
}