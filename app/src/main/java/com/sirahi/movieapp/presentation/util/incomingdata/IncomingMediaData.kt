package com.sirahi.movieapp.presentation.util.incomingdata

import com.sirahi.movieapp.model.MediaResult

sealed class IncomingMediaData{
    class Success(val data: List<MediaResult>): IncomingMediaData()
    class Failure(val data: List<MediaResult>?,val error:String): IncomingMediaData()
    object Loading:IncomingMediaData()
}
