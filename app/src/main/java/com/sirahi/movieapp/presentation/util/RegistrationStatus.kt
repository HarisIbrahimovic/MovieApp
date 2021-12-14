package com.sirahi.movieapp.presentation.util

sealed class RegistrationStatus{
    object Success :RegistrationStatus()
    object Loading :RegistrationStatus()
    object Pending :RegistrationStatus()
    class Failure(val error:RegError):RegistrationStatus()
}
