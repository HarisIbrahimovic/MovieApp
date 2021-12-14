package com.sirahi.movieapp.model.people

data class ActorMovieCredits(
    val movieId:Int,
    val actorId: Int,
    val movieTitle: String,
    val character: String,
    val voteAverage: Double
)