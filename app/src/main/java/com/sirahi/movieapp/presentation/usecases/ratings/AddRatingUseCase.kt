package com.sirahi.movieapp.presentation.usecases.ratings

import com.sirahi.movieapp.data.firebase.Rating
import com.sirahi.movieapp.repository.RatingRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddRatingUseCase@Inject constructor(private val repository: RatingRepository) {

    suspend fun invoke(id:Int,comment:String,value:Double,userName:String,type:String){
        val date = getCurrentDateTime()
        val dateInString = date.toString("dd/MM/yyyy")
        val rating = Rating(userName, dateInString, comment, value)
        if (value != 0.0 && comment != "")repository.addMovieRating(id, rating, type)
    }


    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}