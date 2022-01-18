package com.sirahi.movieapp.presentation.usecases.details.actor

import com.sirahi.movieapp.model.movie.MovieDetails
import com.sirahi.movieapp.model.people.Actor
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorDetails
import com.sirahi.movieapp.repository.DetailMovieRepository
import com.sirahi.movieapp.repository.DetailsActorRepository
import javax.inject.Inject

class GetActorDetailsUseCase
@Inject
constructor(private val repository: DetailsActorRepository) {

    suspend fun invoke(id: Int): Actor {
        when (val response = repository.getActorDetails(id)) {
            is Response.Success ->
                return response.data ?: Actor()
            is Response.Error ->
                response.data ?: Actor()
        }
        return Actor()
    }

}