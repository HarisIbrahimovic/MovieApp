package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieCast
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingTvDetails
import com.sirahi.movieapp.repository.DetailsTvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailsViewModel
    @Inject constructor(
            private val repository: DetailsTvRepository
    )
    :ViewModel() {
        val tvDetails = MutableLiveData<IncomingTvDetails>()

        fun initData(id:Int){
            setTvDetails(id)
            setTvCast(id)
        }

    private fun setTvDetails(id:Int)= viewModelScope.launch (Dispatchers.IO){
        tvDetails.postValue(IncomingTvDetails.Loading)
        when(val response = repository.getTvDetails(id)){
            is Response.Success->tvDetails.postValue(IncomingTvDetails.Success(response.data!!))
            is Response.Error->tvDetails.postValue(IncomingTvDetails.Failure(response.data!!,response.errorMessage!!))
        }
    }

    private val _tvCastDetails = MutableLiveData<IncomingMovieCast>()
    val tvCastDetails:LiveData<IncomingMovieCast> = _tvCastDetails

    private fun setTvCast(id: Int)= viewModelScope.launch(Dispatchers.IO){
        _tvCastDetails.postValue(IncomingMovieCast.Loading)
        when(val response = repository.getTvCredits(id)){
            is Response.Success->_tvCastDetails.postValue(IncomingMovieCast.Success(response.data!!))
            is Response.Error->_tvCastDetails.postValue(IncomingMovieCast.Failure(response.data,response.errorMessage!!))
        }
    }

}