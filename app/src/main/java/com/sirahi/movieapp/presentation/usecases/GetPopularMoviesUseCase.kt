package com.sirahi.movieapp.presentation.usecases

import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMoviesUseCase
@Inject
constructor(private val menuRepository: MenuRepository) {
    fun invoke(): Flow<IncomingMediaData> = flow {
        emit(IncomingMediaData.Loading)
        when (val response = menuRepository.getMovies("popular")) {
            is Response.Success -> emit(IncomingMediaData.Success(response.data!!))
            is Response.Error -> emit(
                IncomingMediaData.Failure(
                    response.data,
                    response.errorMessage!!
                )
            )
        }
    }
}