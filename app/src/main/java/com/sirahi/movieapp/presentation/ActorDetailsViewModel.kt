package com.sirahi.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.model.people.Actor
import com.sirahi.movieapp.presentation.usecases.details.actor.GetActorDetailsUseCase
import com.sirahi.movieapp.presentation.usecases.details.actor.GetActorMovieCreditsUseCase
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorCredits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorDetailsViewModel
@Inject
constructor(
    private val getActorDetailsUseCase: GetActorDetailsUseCase,
    private val getActorMovieCreditsUseCase: GetActorMovieCreditsUseCase
    ) : ViewModel() {

    val actorDetails= Actor()
    val incomingActorCredits = IncomingActorCredits()

    fun setActorData(id: Int) {
        setActorDetails(id)
        setActorCredits(id)
    }

    private fun setActorCredits(id: Int) =viewModelScope.launch (Dispatchers.IO){
        incomingActorCredits.setValues(getActorMovieCreditsUseCase.inovke(id))
    }

    private fun setActorDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        actorDetails.setValues(getActorDetailsUseCase.invoke(id))
    }

}