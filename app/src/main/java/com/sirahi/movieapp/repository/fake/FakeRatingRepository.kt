package com.sirahi.movieapp.repository.fake

import androidx.lifecycle.LiveData
import com.sirahi.movieapp.data.firebase.Rating
import com.sirahi.movieapp.repository.RatingRepository

class FakeRatingRepository:RatingRepository {

    override suspend fun addMovieRating(movieId: Int, rating: Rating, type: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserName(): LiveData<String> {
        TODO("Not yet implemented")
    }

    override fun getMovieRatings(id: Int, type: String): LiveData<List<Rating>> {
        TODO("Not yet implemented")
    }

    override fun setMovieRatings(id: Int, type: String) {
        TODO("Not yet implemented")
    }

    override fun setUserName() {
        TODO("Not yet implemented")
    }
}