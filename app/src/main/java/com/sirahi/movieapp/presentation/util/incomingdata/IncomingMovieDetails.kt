package com.sirahi.movieapp.presentation.util.incomingdata

import com.sirahi.movieapp.model.movie.MovieDetails

sealed class IncomingMovieDetails {
    class Success(val data: MovieDetails): IncomingMovieDetails()
    class Failure(val data: MovieDetails?, val error:String): IncomingMovieDetails()
    object Loading:IncomingMovieDetails()
}