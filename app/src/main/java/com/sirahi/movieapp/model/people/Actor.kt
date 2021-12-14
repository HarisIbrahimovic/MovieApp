package com.sirahi.movieapp.model.people

data class Actor(
    val name: String,
    val biography: String,
    val birthday: String,
    val deathday: String?,
    val profilePath: String,
    val placeOfBirth : String,
)