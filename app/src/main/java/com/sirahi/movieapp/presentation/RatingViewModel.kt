package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.data.firebase.Rating
import com.sirahi.movieapp.model.RatingUIState
import com.sirahi.movieapp.presentation.usecases.ratings.AddRatingUseCase
import com.sirahi.movieapp.repository.RatingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val repository: RatingRepository,
    private val addRatingUseCase: AddRatingUseCase
) : ViewModel() {

    private var mediaId = -1
    lateinit var type: String
    lateinit var ratingList: LiveData<List<Rating>>
    private lateinit var userName: LiveData<String>
    var ratingValue = RatingUIState()

    fun initData(id: Int, type: String) {
        mediaId = id
        ratingList = repository.getMovieRatings(id, type)
        viewModelScope.launch(Dispatchers.IO) {
            repository.setMovieRatings(id, type)
            userName = repository.getUserName()
        }
    }

    fun addRating() = viewModelScope.launch(Dispatchers.IO) {
        addRatingUseCase.invoke(mediaId, ratingValue.comment, ratingValue.value.toDouble(), userName.value ?: "", type)
    }
}