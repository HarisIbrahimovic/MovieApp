package com.sirahi.movieapp.presentation.usecases.details.actor

import com.sirahi.movieapp.model.people.ActorMovieCredits
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorCredits
import com.sirahi.movieapp.repository.DetailsActorRepository
import javax.inject.Inject

class GetActorMovieCreditsUseCase
@Inject
constructor(private val repository: DetailsActorRepository) {

    suspend fun inovke(id: Int): IncomingActorCredits {
        val response = repository.getActorMovieCredits(id)
        return IncomingActorCredits(
            (response.data ?: ArrayList()) as ArrayList<ActorMovieCredits>,
            response.errorMessage ?: ""
        )
    }


}