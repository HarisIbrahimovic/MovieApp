package com.sirahi.movieapp.presentation.usecases.details.actor

import com.sirahi.movieapp.model.people.Actor
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.DetailsActorRepository
import javax.inject.Inject

class GetActorDetailsUseCase
@Inject
constructor(private val repository: DetailsActorRepository) {

    suspend fun invoke(id: Int): Actor {
        return repository.getActorDetails(id).data ?: Actor()
    }

}