package com.sirahi.movieapp.presentation.usecases

import androidx.lifecycle.liveData
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetTvByCategoryUseCase
@Inject
constructor(private val repository: MenuRepository) {

    fun invoke(category: String) = liveData(Dispatchers.IO) {
        emit(IncomingMediaData.Loading)
        when (val response = repository.getTv(category)) {
            is Response.Success -> response.data?.let {
                emit(
                    IncomingMediaData.Success(
                        it
                    )
                )
            }
            is Response.Error ->
                response.errorMessage?.let {
                    emit(
                        IncomingMediaData.Failure(
                            response.data,
                            it
                        )
                    )
                }
        }
    }

}