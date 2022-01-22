package com.sirahi.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.model.tv.TVDetails
import com.sirahi.movieapp.presentation.usecases.details.tv.AddToWatchlistTvUseCase
import com.sirahi.movieapp.presentation.usecases.details.tv.GetTvCastUseCase
import com.sirahi.movieapp.presentation.usecases.details.tv.GetTvDetailsUseCase
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaCast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailsViewModel @Inject constructor(
    private val getTvCastUseCase: GetTvCastUseCase,
    private val getTvDetailsUseCase: GetTvDetailsUseCase,
    private val addToWatchlistTvUseCase: AddToWatchlistTvUseCase
) :
    ViewModel() {

    val tvCastObservable = IncomingMediaCast()
    val tvDetailsObservable = TVDetails()
    private var tvId = -1

    fun initData(id: Int) {
        if (tvId == -1) {
            setTvDetails(id)
            setTvCast(id)
            tvId = id
        }
    }

    private fun setTvDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        tvDetailsObservable.setValues(getTvDetailsUseCase.invoke(id))
    }

    private fun setTvCast(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        tvCastObservable.setValues(getTvCastUseCase.invoke(id))
    }

    fun addToWatchList() {
        addToWatchlistTvUseCase.addToWatchlist(tvDetailsObservable, tvId)
    }

    fun addToFavorites() {
        addToWatchlistTvUseCase.addToFavorites(tvDetailsObservable, tvId)
    }

}