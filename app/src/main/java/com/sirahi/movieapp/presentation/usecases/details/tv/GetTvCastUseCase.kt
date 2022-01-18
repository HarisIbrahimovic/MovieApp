package com.sirahi.movieapp.presentation.usecases.details.tv

import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorCredits
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaCast
import com.sirahi.movieapp.repository.DetailsTvRepository
import javax.inject.Inject

class GetTvCastUseCase @Inject constructor(private val repository: DetailsTvRepository) {

    suspend fun invoke(id:Int): IncomingMediaCast {
        val response = repository.getTvCredits(id)
        return IncomingMediaCast(
            (response.data ?: ArrayList()) as ArrayList<MovieCast>,
            response.errorMessage ?: ""
        )
    }

}