package com.sirahi.movieapp.presentation.usecases

import androidx.lifecycle.liveData
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaDataDelegates
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMoviesByCategoryUseCase
@Inject
constructor(private val menuRepository: MenuRepository) {

    suspend fun invoke(category:String): IncomingMediaDataDelegates {
        val incomingMediaData = IncomingMediaDataDelegates()
        when (val response = menuRepository.getMovies(category)) {
            is Response.Success ->
                incomingMediaData.mediaList = response.data as ArrayList<MediaResult>
            is Response.Error -> {
                incomingMediaData.errorMessage = response.errorMessage
                incomingMediaData.mediaList = response.data as ArrayList<MediaResult>
            }
        }
        return incomingMediaData
    }

}