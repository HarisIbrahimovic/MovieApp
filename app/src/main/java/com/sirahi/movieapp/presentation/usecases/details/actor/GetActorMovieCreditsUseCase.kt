package com.sirahi.movieapp.presentation.usecases.details.actor

import com.sirahi.movieapp.model.people.ActorMovieCredits
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorCredits
import com.sirahi.movieapp.repository.DetailsActorRepository
import javax.inject.Inject

class GetActorMovieCreditsUseCase
@Inject
constructor(private val repository: DetailsActorRepository) {


    suspend fun invoke(id: Int):IncomingActorCredits{
        val incomingActorCredits = IncomingActorCredits()
        when(val response = repository.getActorMovieCredits(id)){
            is Response.Success->incomingActorCredits.creditList= response.data as ArrayList<ActorMovieCredits>
            is Response.Error->{
                incomingActorCredits.errorMessage = response.errorMessage
                incomingActorCredits.creditList = response.data as ArrayList<ActorMovieCredits>
            }
        }
        return incomingActorCredits
    }


}