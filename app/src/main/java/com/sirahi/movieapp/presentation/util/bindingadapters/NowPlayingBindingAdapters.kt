package com.sirahi.movieapp.presentation.util.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaDataDelegates
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter


@BindingAdapter("setMovieList", "setList")
fun RecyclerView.setMovieList( adapter: VerticalMediaAdapter, list: ArrayList<MediaResult>) {
    adapter.setList(list)
}