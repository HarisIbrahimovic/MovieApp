package com.sirahi.movieapp.presentation.usecases.signup

import com.sirahi.movieapp.presentation.util.Constants
import com.sirahi.movieapp.presentation.util.RegError
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.repository.SignUpRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase
@Inject
constructor(private val repository: SignUpRepository) {

    suspend fun checkUser() :RegistrationStatus{
        return if (repository.checkUser())
            (RegistrationStatus.Success)
        else
            (RegistrationStatus.Pending)
    }


    suspend fun registerUser(
        email: String,
        username: String,
        password: String
    ): RegistrationStatus {
        val emptyError = checkEmpty(username, email, password)
        val formatError = checkLength(username, password)
        if (emptyError != null) return RegistrationStatus.Failure(emptyError)
        if (formatError != null) return (RegistrationStatus.Failure(formatError))
        return if (repository.registerUser(username, email, password))
            RegistrationStatus.Success
        else
            RegistrationStatus.Failure(RegError.UnknownError())
    }


    suspend fun loginUser(email: String, password: String): RegistrationStatus {
        val emptyError = checkEmpty(email = email, password = password)
        if (emptyError != null) return RegistrationStatus.Failure(emptyError)
        return if (repository.loginUser(email, password))
             RegistrationStatus.Success
        else
             RegistrationStatus.Failure(RegError.UnknownError())
    }

    private fun checkLength(username: String, password: String): RegError? {
        if (username.length <= Constants.UP_MIN)
            return (RegError.TooShort(field = "username"))
        if (password.length <= Constants.UP_MIN)
            return (RegError.TooShort(field = "password"))
        if (username.length >= Constants.UP_MAX)
            return (RegError.TooLong(field = "username"))
        if (password.length >= Constants.UP_MAX)
            return (RegError.TooLong(field = "password"))
        if (username.length > Constants.UP_MIN || username.length < Constants.UP_MAX || password.length > Constants.UP_MIN || password.length < Constants.UP_MAX) {
            return null
        }
        return RegError.UnknownError()
    }

    private fun checkEmpty(
        username: String = "username",
        email: String,
        password: String
    ): RegError? {
        if (username.isEmpty())
            return (RegError.EmptyField(field = "username"))
        if (email.isEmpty())
            return (RegError.EmptyField(field = "email"))
        if (password.isEmpty())
            return (RegError.EmptyField(field = "password"))
        return null
    }


}

