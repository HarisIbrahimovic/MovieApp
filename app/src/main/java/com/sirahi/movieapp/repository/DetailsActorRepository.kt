package com.sirahi.movieapp.repository

import com.sirahi.movieapp.model.people.Actor
import com.sirahi.movieapp.model.people.ActorMovieCredits
import com.sirahi.movieapp.presentation.util.Response

interface DetailsActorRepository {
    suspend fun getActorDetails(id: Int): Response<Actor>
    suspend fun getActorMovieCredits(id: Int): Response<List<ActorMovieCredits>>
}