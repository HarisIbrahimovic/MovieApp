package com.sirahi.movieapp.presentation.util.incomingdata

import com.sirahi.movieapp.model.MediaResult

sealed class IncomingMediaData(val data: List<MediaResult>?) {
    class Success(data: List<MediaResult>) : IncomingMediaData(data)
    class Failure(data: List<MediaResult>?, val error: String) : IncomingMediaData(data)
    object Loading : IncomingMediaData(null)
}
