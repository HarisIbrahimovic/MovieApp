package com.sirahi.movieapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sirahi.movieapp.R
import com.sirahi.movieapp.model.Genre

class GenreAdapter(private val context: Context, private val clickListener: ClickListener) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private var genreList: List<Genre>? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.genreButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.genre_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genreList!![position]
        holder.button.text = genre.name
        holder.button.setOnClickListener {
            clickListener.genreClicked(holder.adapterPosition)
        }
        if (genre.clicked) {
            holder.button.background =
                ContextCompat.getDrawable(context, R.drawable.genre_design_selected)
            holder.button.setTextColor(ContextCompat.getColor(context, R.color.white))
        } else {
            holder.button.background = ContextCompat.getDrawable(context, R.drawable.genre_design)
            holder.button.setTextColor(ContextCompat.getColor(context, R.color.white))
        }

    }

    override fun getItemCount(): Int {
        return genreList?.size ?: 0
    }

    fun setList(list: List<Genre>) {
        genreList = list
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun genreClicked(id: Int)
    }
}