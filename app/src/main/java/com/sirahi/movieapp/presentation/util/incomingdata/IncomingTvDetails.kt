package com.sirahi.movieapp.presentation.util.incomingdata

import com.sirahi.movieapp.model.movie.MovieDetails
import com.sirahi.movieapp.model.tv.TVDetails

sealed class IncomingTvDetails(val data: TVDetails?) {
    class Success(data: TVDetails): IncomingTvDetails(data)
    class Failure(data: TVDetails?, val error:String): IncomingTvDetails(data)
    object Loading:IncomingTvDetails(null)
}