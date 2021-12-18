package com.sirahi.movieapp.presentation.util

sealed class RegistrationStatus(val error:RegError?){
    object Success :RegistrationStatus(null)
    object Loading :RegistrationStatus(null)
    object Pending :RegistrationStatus(null)
    class Failure(error:RegError):RegistrationStatus(error)
}
