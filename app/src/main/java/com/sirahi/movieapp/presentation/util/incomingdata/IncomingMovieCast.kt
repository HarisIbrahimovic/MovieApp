package com.sirahi.movieapp.presentation.util.incomingdata

import com.sirahi.movieapp.model.movie.MovieCast

sealed class IncomingMovieCast{
    class Success(val data: List<MovieCast>): IncomingMovieCast()
    class Failure(val data: List<MovieCast>?, val error:String): IncomingMovieCast()
    object Loading:IncomingMovieCast()
}
