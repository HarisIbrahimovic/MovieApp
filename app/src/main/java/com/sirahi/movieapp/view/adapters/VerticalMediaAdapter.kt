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

class VerticalMediaAdapter(private val context: Context): RecyclerView.Adapter<VerticalMediaAdapter.ViewHolder>() {

    private var vList:List<MediaResult>?=null

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val image:ImageView = view.findViewById(R.id.mediaImageVertical)
        val name:TextView = view.findViewById(R.id.media_name_v)
        val overView:TextView = view.findViewById(R.id.overViewVertical)
        val scoreBar:RatingBar = view.findViewById(R.id.ratingBarVertical)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.search_item_design,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = vList!![position]
        holder.name.text=result.title
        holder.overView.text=result.overview
        holder.scoreBar.progress=result.score.toInt()
        Glide.with(context).load(ApiConstants.URL_START+result.posterPath).apply(RequestOptions.bitmapTransform(RoundedCorners(40))).into(holder.image)
    }

    override fun getItemCount(): Int {
        return vList?.size ?: 0
    }

    fun setList(data: List<MediaResult>) {
        vList=data
        notifyDataSetChanged()
    }
}