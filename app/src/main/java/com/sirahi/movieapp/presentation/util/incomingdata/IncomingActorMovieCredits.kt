package com.sirahi.movieapp.presentation.util.incomingdata

import com.sirahi.movieapp.model.people.ActorMovieCredits

sealed class IncomingActorMovieCredits{
    class Success(val data: List<ActorMovieCredits>): IncomingActorMovieCredits()
    class Failure(val data: List<ActorMovieCredits>?, val error:String): IncomingActorMovieCredits()
    object Loading:IncomingActorMovieCredits()
}