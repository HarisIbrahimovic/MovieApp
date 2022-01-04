package com.sirahi.movieapp.repository

import androidx.lifecycle.LiveData
import com.sirahi.movieapp.data.firebase.Rating

interface RatingRepository {
    fun getMovieRatings(id: Int, type: String): LiveData<List<Rating>>
    suspend fun getUserName(): LiveData<String>
    fun setUserName()
    suspend fun addMovieRating(movieId: Int, rating: Rating, type: String)
    fun setMovieRatings(id: Int, type: String)
}