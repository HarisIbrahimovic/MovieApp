package com.sirahi.movieapp.presentation.usecases

import androidx.lifecycle.liveData
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMoviesByCategoryUseCase
@Inject
constructor(private val menuRepository: MenuRepository) {

    fun invoke(category:String) = liveData(Dispatchers.IO) {
        emit(IncomingMediaData.Loading)
        when (val response = menuRepository.getMovies(category)) {
            is Response.Success ->
                response.data?.let {
                    emit(IncomingMediaData.Success(it))
            }
            is Response.Error -> emit(
                IncomingMediaData.Failure(
                    response.data,
                    response.errorMessage!!
                )
            )
        }
    }

}