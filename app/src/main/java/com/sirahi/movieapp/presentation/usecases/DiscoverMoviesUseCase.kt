package com.sirahi.movieapp.presentation.usecases

import androidx.lifecycle.liveData
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DiscoverMoviesUseCase
@Inject
constructor(
    private val repository: MenuRepository
) {
    fun invoke(id: Int, name: String) = flow {
        emit(IncomingMediaData.Loading)
        when (val response = repository.discoverMovies(id, name)) {
            is Response.Success -> response.data?.let {
                emit(
                    IncomingMediaData.Success(
                        it
                    )
                )
            }
            is Response.Error ->
                emit(
                    IncomingMediaData.Failure(
                        response.data,
                        response.errorMessage
                    )
                )
        }
    }
}