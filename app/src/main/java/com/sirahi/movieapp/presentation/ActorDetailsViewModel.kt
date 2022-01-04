package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorDetails
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorMovieCredits
import com.sirahi.movieapp.repository.DetailsActorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorDetailsViewModel
@Inject constructor(private val repository: DetailsActorRepository) : ViewModel() {

    private val _actorDetails = MutableLiveData<IncomingActorDetails>()
    val actorDetails: LiveData<IncomingActorDetails> = _actorDetails

    private val _actorCredits = MutableLiveData<IncomingActorMovieCredits>()
    val actorCredits: LiveData<IncomingActorMovieCredits> = _actorCredits


    fun setActorData(id: Int) {
        setActorDetails(id)
        setActorCredits(id)
    }

    private fun setActorCredits(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        _actorCredits.postValue(IncomingActorMovieCredits.Loading)
        when (val response = repository.getActorMovieCredits(id)) {
            is Response.Success -> _actorCredits.postValue(
                response.data?.let {
                    IncomingActorMovieCredits.Success(
                        it
                    )
                }
            )
            is Response.Error -> _actorCredits.postValue(
                response.errorMessage?.let {
                    IncomingActorMovieCredits.Failure(
                        response.data,
                        it
                    )
                }
            )
        }
    }

    private fun setActorDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        _actorDetails.postValue(IncomingActorDetails.Loading)
        when (val response = repository.getActorDetails(id)) {
            is Response.Success -> _actorDetails.postValue(IncomingActorDetails.Success(response.data!!))
            is Response.Error -> _actorDetails.postValue(
                response.errorMessage?.let {
                    IncomingActorDetails.Failure(
                        response.data,
                        it
                    )
                }
            )
        }

    }


}