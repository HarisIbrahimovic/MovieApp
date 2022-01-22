package com.sirahi.movieapp.presentation.usecases.details

import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorCredits
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaCast
import com.sirahi.movieapp.repository.DetailMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieCastUseCase
@Inject
constructor(private val repository: DetailMovieRepository) {

    suspend fun invoke(id: Int): IncomingMediaCast {
        val incomingMediaCast = IncomingMediaCast()
        when(val response = repository.getMovieCredits(id)){
            is Response.Success->incomingMediaCast.castList= response.data as ArrayList<MovieCast>
            is Response.Error->{
                incomingMediaCast.errorMessage = response.errorMessage
                incomingMediaCast.castList= response.data as ArrayList<MovieCast>
            }
        }
        return incomingMediaCast
    }
}