package com.sirahi.movieapp.presentation.util

sealed class RegError(val mess: String,val field:String=""){
    class EmptyField(error:String = Constants.FIELD_REQ,field: String):RegError(error,field)
    class InvalidEmail(error: String = Constants.INVALID_INPUT,field: String):RegError(error,field)
    class TooShort(error: String = Constants.MIN_CHAR,field: String):RegError(error,field)
    class TooLong(error: String = Constants.MAX_CHAR,field: String):RegError(error,field)
    class UnknownError(error: String = Constants.UNK_ERROR,field: String=""):RegError(error,field)
}
