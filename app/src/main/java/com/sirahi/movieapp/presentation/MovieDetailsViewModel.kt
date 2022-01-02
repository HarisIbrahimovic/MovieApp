package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieCast
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.repository.DetailMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel
    @Inject constructor (private val repository:DetailMovieRepository)
    :ViewModel(){

    private val _movieDetails = MutableLiveData<IncomingMovieDetails>()
    private val _movieCast = MutableLiveData<IncomingMovieCast>()

    val movieDetails:LiveData<IncomingMovieDetails> = _movieDetails
    val movieCast: LiveData<IncomingMovieCast> = _movieCast

    fun init(id:Int) {
        if(_movieCast.value==null){
            setMovieDetails(id)
            setMovieCast(id)
        }
    }

    private fun setMovieCast(id: Int)= viewModelScope.launch(Dispatchers.IO){
        _movieCast.postValue(IncomingMovieCast.Loading)
        when(val response = repository.getMovieCredits(id)){
            is Response.Success->_movieCast.postValue(IncomingMovieCast.Success(response.data!!))
            is Response.Error->_movieCast.postValue(IncomingMovieCast.Failure(response.data,response.errorMessage!!))
        }
    }

    private fun setMovieDetails(id:Int)=viewModelScope.launch (Dispatchers.IO){
        _movieDetails.postValue(IncomingMovieDetails.Loading)
        when(val response = repository.getMovieDetails(id)){
            is Response.Success -> {
                _movieDetails.postValue(IncomingMovieDetails.Success(response.data!!))

            }
            is Response.Error -> _movieDetails.postValue(
                IncomingMovieDetails.Failure(
                    response.data,
                    response.errorMessage!!
                )
            )
        }
    }

    fun addMovieToFavorites(id:Int,title:String,score:Double,imagePath:String){
        val movie = MediaItem(id,title, imagePath, score)
        repository.addToFavorites(movie)
    }

}