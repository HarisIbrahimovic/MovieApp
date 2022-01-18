package com.sirahi.movieapp.presentation.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sirahi.movieapp.model.people.ActorMovieCredits
import com.sirahi.movieapp.view.adapters.ActorMovieCreditsAdapter


@BindingAdapter("setActorMovieCreditsAdapter")
fun setActorMovieCreditsAdapter(view: RecyclerView, adapter: ActorMovieCreditsAdapter) {
    view.adapter = adapter
}


@BindingAdapter("setMovieList", "setList")
fun setCastList(view:RecyclerView, adapter: ActorMovieCreditsAdapter, list: ArrayList<ActorMovieCredits>) {
    adapter.setList(list)
}




