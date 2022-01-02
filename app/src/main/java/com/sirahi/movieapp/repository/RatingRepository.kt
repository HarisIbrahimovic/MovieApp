package com.sirahi.movieapp.repository

import androidx.lifecycle.LiveData
import com.sirahi.movieapp.data.firebase.Rating

interface RatingRepository {
    fun getMovieRatings(id:Int): LiveData<List<Rating>>
    fun getUserName():LiveData<String>
    fun setUserName()
    fun addMovieRating(movieId:Int, rating: Rating)
    fun setMovieRatings(id: Int)
}