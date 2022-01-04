package com.sirahi.movieapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.R
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.model.MediaResult

class MovieResultAdapter(private val context: Context, private val clickListener: ClickListener) :
    RecyclerView.Adapter<MovieResultAdapter.ViewHolder>() {

    private var listOfMedia: List<MediaResult>? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val mediaName: TextView = view.findViewById(R.id.media_name)
        val mediaScore: RatingBar = view.findViewById(R.id.ratingBarVertical)
        val mediaImage: ImageView = view.findViewById(R.id.media_item_image)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener.onMovieClikced(listOfMedia!![adapterPosition].id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.media_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (listOfMedia == null) 0
        else listOfMedia!!.size
    }

    override fun onBindViewHolder(holder: MovieResultAdapter.ViewHolder, position: Int) {
        val requestOptions = RequestOptions.centerCropTransform()
        val media = listOfMedia!![position]
        holder.mediaName.text = media.title
        holder.mediaScore.progress = media.score.toInt()
        Glide.with(context).load(ApiConstants.URL_START + media.posterPath).apply(requestOptions)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(50))).into(holder.mediaImage)
    }

    fun setList(list: List<MediaResult>) {
        listOfMedia = list
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onMovieClikced(id: Int)
    }
}