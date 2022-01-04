package com.sirahi.movieapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sirahi.movieapp.R
import com.sirahi.movieapp.data.firebase.Rating

class RatingAdapter(private val context: Context) :
    RecyclerView.Adapter<RatingAdapter.ViewHolder>() {

    private var list: List<Rating>? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.usernameRating)
        val userRating: TextView = view.findViewById(R.id.ratingScore)
        val ratingDate: TextView = view.findViewById(R.id.dateRating)
        val ratingComment: TextView = view.findViewById(R.id.commentRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rating_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rating = list!![position]
        holder.userName.text = rating.username
        holder.userRating.text = rating.value.toString()
        holder.ratingComment.text = rating.comment
        holder.ratingDate.text = rating.date
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun setList(newList: List<Rating>) {
        list = newList
        notifyDataSetChanged()
    }
}