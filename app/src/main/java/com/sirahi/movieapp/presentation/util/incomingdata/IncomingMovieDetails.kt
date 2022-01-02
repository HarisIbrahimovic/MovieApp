package com.sirahi.movieapp.presentation.util.incomingdata

import com.sirahi.movieapp.model.movie.MovieDetails

sealed class IncomingMovieDetails(val data:MovieDetails?) {
    class Success(data: MovieDetails): IncomingMovieDetails(data)
    class Failure(data: MovieDetails?, val error:String): IncomingMovieDetails(data)
    object Loading:IncomingMovieDetails(null)
}