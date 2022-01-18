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
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.data.remote.util.ApiConstants

class UserListAdapter(private val context: Context, private val type: Int, private val onUserListListener: OnUserListListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: ArrayList<MediaItem>? = null
    private lateinit var mediaName: TextView
    private lateinit var mediaScore: RatingBar
    private lateinit var mediaImage: ImageView

    inner class FavViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(v: View?) {
            onUserListListener.onFavUserListClickListener(
                list!![adapterPosition].id,
                list!![adapterPosition].type
            )
        }
        init {
            view.setOnClickListener(this)
            setFields(view)
        }
    }

    inner class WatchViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        override fun onClick(v: View?) {
            onUserListListener.onWatchUserListClickListener(
                list!![adapterPosition].id,
                list!![adapterPosition].type
            )
        }
        init {
            view.setOnClickListener(this)
            setFields(view)
        }
    }

    fun setList(list: ArrayList<MediaItem>) {
        this.list = list
    }

    fun setFields(view: View) {
        mediaName = view.findViewById(R.id.media_name)
        mediaScore = view.findViewById(R.id.ratingBarVertical)
        mediaImage = view.findViewById(R.id.media_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false)
        return if (type == 1) FavViewHolder(view)
        else WatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = list!![position]
        mediaName.text = result.title
        mediaScore.progress = result.score.toInt()
        Glide.with(context).load(ApiConstants.URL_START + result.imagePath)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(40))).into(mediaImage)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    interface OnUserListListener {
        fun onFavUserListClickListener(id: Int, type: String)
        fun onWatchUserListClickListener(id: Int, type: String)
    }
}