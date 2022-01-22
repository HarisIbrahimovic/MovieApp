package com.sirahi.movieapp.presentation.util.bindingadapters

import android.view.View
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sirahi.movieapp.data.firebase.Rating
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.presentation.RatingViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.view.adapters.*



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

@BindingAdapter("checkLoading")
fun checkLoadingMediaData(view: View, value: Boolean) {
    view.visibility = if (value)  View.VISIBLE else View.GONE
}

@BindingAdapter("checkLoadingLiveData")
fun checkLoadingLiveData(view:View, incomingMediaData: IncomingMediaData?){
    if(incomingMediaData is IncomingMediaData.Loading)view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}


@BindingAdapter("setMovieList", "setList")
fun RecyclerView.setMovieList( adapter: MovieResultAdapter, list: ArrayList<MediaResult>) {
    adapter.setList(list)
}

@BindingAdapter("setTvList", "setList")
fun RecyclerView.setTvList( adapter: TvResultAdapter, list: ArrayList<MediaResult>) {
    adapter.setList(list)
}

@BindingAdapter("setRatingAdapter")
fun setRatingAdapter(view: RecyclerView, adapter: RatingAdapter) {
    view.adapter=adapter
}


@BindingAdapter("setRatingList", "setList")
fun RecyclerView.setTvList( adapter: RatingAdapter, newList: List<Rating>?) {
    if (newList != null) {
        adapter.setList(newList)
    }
}

@BindingAdapter("setRating")
fun setRating(view:RatingBar,score:Double){
    view.progress=score.toInt()
}


@BindingAdapter("onRatingChanged")
fun onRatingChanged(view:RatingBar,viewModel: RatingViewModel){
    view.setOnRatingBarChangeListener { _, rating, _ ->
        viewModel.ratingValue.value=rating
    }
}




