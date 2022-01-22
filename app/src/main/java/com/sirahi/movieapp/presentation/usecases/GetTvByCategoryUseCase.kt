package com.sirahi.movieapp.presentation.usecases

import androidx.lifecycle.liveData
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaDataDelegates
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetTvByCategoryUseCase
@Inject
constructor(private val repository: MenuRepository) {

    suspend fun invoke(category: String): IncomingMediaDataDelegates {
        val incomingMediaData = IncomingMediaDataDelegates()
        when (val response = repository.getTv(category)) {
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