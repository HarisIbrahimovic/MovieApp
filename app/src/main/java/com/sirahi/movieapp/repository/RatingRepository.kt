package com.sirahi.movieapp.repository

import androidx.lifecycle.LiveData
import com.sirahi.movieapp.data.firebase.Rating

interface RatingRepository {
    suspend fun addMovieRating(movieId: Int, rating: Rating, type: String)
    suspend fun getUserName(): LiveData<String>
    fun getMovieRatings(id: Int, type: String): LiveData<List<Rating>>
    fun setMovieRatings(id: Int, type: String)
    fun setUserName()
}