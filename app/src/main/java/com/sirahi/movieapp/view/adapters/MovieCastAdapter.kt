package com.sirahi.movieapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.R
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.model.movie.MovieCast

class MovieCastAdapter(private val actorListener:ActorClickListener,private val context: Context) : RecyclerView.Adapter<MovieCastAdapter.ViewHolder>() {

    private var list: List<MovieCast>?=null


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val actorImage:ImageView = view.findViewById(R.id.actorImage)
        val actorName:TextView = view.findViewById(R.id.actorName)
        val characterName:TextView = view.findViewById(R.id.characterName)
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            list?.get(adapterPosition)?.let { actorListener.onActorClicked(it.actorId) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.actor_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = list!![position]
        holder.actorName.text = actor.actorName
        holder.characterName.text = actor.characterName
        Glide.with(context)
            .load(ApiConstants.URL_START+actor.profilePath)
            .apply(RequestOptions.bitmapTransform( RoundedCorners(25)))
            .into(holder.actorImage)
    }

    override fun getItemCount(): Int {
        return if(list==null)0
        else list!!.size
    }

    fun setList(newList: List<MovieCast>?) {
        list = newList
        notifyDataSetChanged()
    }

    interface ActorClickListener{
        fun onActorClicked(id:Int)
    }
}