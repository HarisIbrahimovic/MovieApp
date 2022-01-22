package com.sirahi.movieapp.repository.fake

import com.sirahi.movieapp.model.people.Actor
import com.sirahi.movieapp.model.people.ActorMovieCredits
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.DetailsActorRepository

class FakeActorDetailsRepository : DetailsActorRepository {
    override suspend fun getActorDetails(id: Int): Response<Actor> {
        TODO("Not yet implemented")
    }

    override suspend fun getActorMovieCredits(id: Int): Response<List<ActorMovieCredits>> {
        TODO("Not yet implemented")
    }
}