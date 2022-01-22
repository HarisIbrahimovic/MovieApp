package com.sirahi.movieapp.presentation.util.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.view.adapters.MovieCastAdapter
import com.sirahi.movieapp.view.adapters.UserListAdapter


@BindingAdapter("setUserListAdapter")
fun setUserListAdapter(view:RecyclerView,adapter:UserListAdapter){
    view.adapter=adapter
}



@BindingAdapter("setUserList", "setList")
fun RecyclerView.setUserList( adapter: UserListAdapter, list: ArrayList<MediaItem>) {
    adapter.setList(list)
}