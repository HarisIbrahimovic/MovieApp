package com.sirahi.movieapp.repository

import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.model.movie.MovieDetails
import com.sirahi.movieapp.presentation.util.Response

interface DetailMovieRepository {
    suspend fun getMovieDetails(id:Int): Response<MovieDetails>
    suspend fun getMovieCredits(id:Int):Response<List<MovieCast>>
    fun addToFavorites(mediaItem: MediaItem)
}