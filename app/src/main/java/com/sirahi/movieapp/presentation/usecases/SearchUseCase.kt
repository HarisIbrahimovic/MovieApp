package com.sirahi.movieapp.presentation.usecases

import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: MenuRepository) {

    private val searchList = ArrayList<MediaResult>()

    fun invoke(query: String) = flow {
        when (val response = repository.getSearchDataMovies(query)) {
            is Response.Success -> {
                searchList.clear()
                response.data?.let { searchList.addAll(it) }
            }
            is Response.Error ->
                response.errorMessage?.let {
                    emit(
                        IncomingMediaData.Failure(
                            null,
                            it
                        )
                    )
                }
        }
        when (val response = repository.getSearchDataTV(query)) {
            is Response.Success -> {
                response.data?.let { searchList.addAll(it) }
                emit(IncomingMediaData.Success(searchList))
            }
            is Response.Error ->
                response.errorMessage?.let {
                    emit(
                        IncomingMediaData.Failure(
                            null,
                            it
                        )
                    )
                }
        }
    }
}
