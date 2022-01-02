package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sirahi.movieapp.data.firebase.Rating
import com.sirahi.movieapp.repository.RatingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
        private val  repository: RatingRepository
    ) :ViewModel() {

    private lateinit var userName  : LiveData<String>
    lateinit var movieRating  : LiveData<List<Rating>>

    fun initData(id:Int){
        movieRating = repository.getMovieRatings(id)
        userName =  repository.getUserName()
    }


    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun addRating(movieId:Int,comment:String,value:Double){
        val date = getCurrentDateTime()
        val dateInString = date.toString("dd/MM/yyyy")
        val currentUserName = userName.value
        val rating = currentUserName?.let { Rating(it,dateInString,comment, value) }
        if(value==0.0||comment==""){
            return
        }else rating?.let { repository.addMovieRating(movieId, it) }

    }
}