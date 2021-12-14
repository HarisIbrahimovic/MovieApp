package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieCast
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.repository.DetailMovieRepository
import com.sirahi.movieapp.repository.default.DefaultMovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel:ViewModel(){
    private val repository:DetailMovieRepository = DefaultMovieDetailsRepository()

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
            is Response.Success->_movieDetails.postValue(IncomingMovieDetails.Success(response.data!!))
            is Response.Error->_movieDetails.postValue(IncomingMovieDetails.Failure(response.data,response.errorMessage!!))
        }
    }

}