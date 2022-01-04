package com.sirahi.movieapp.presentation.util.incomingdata

import com.sirahi.movieapp.model.movie.MovieDetails
import com.sirahi.movieapp.model.people.Actor

sealed class IncomingActorDetails(val data: Actor?) {
    class Success(data: Actor) : IncomingActorDetails(data)
    class Failure(data: Actor?, val error: String) : IncomingActorDetails(data)
    object Loading : IncomingActorDetails(null)
}