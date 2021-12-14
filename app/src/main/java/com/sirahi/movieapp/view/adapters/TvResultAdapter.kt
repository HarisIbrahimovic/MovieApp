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

class TvResultAdapter(private val context: Context): RecyclerView.Adapter<TvResultAdapter.ViewHolder>() {

    private var listOfMedia:List<MediaResult>?=null

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val mediaName: TextView = view.findViewById(R.id.media_name)
        val mediaScore: RatingBar = view.findViewById(R.id.ratingBarVertical)
        val mediaImage: ImageView = view.findViewById(R.id.media_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.media_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val requestOptions = RequestOptions.centerCropTransform()
        val media = listOfMedia!![position]
        holder.mediaName.text = media.title
        holder.mediaScore.progress=media.score.toInt()
        Glide.with(context).load(ApiConstants.URL_START+media.posterPath).apply(requestOptions).apply(RequestOptions.bitmapTransform( RoundedCorners(50))).into(holder.mediaImage)
    }

    override fun getItemCount(): Int {
        return if(listOfMedia==null)0
        else listOfMedia!!.size
    }

    fun setList(list:List<MediaResult>){
        listOfMedia=list
        notifyDataSetChanged()
    }
}