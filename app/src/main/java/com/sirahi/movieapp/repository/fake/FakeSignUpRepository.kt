package com.sirahi.movieapp.repository.fake

import androidx.lifecycle.MutableLiveData
import com.sirahi.movieapp.presentation.util.RegError
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.repository.SignUpRepository

class FakeSignUpRepository : SignUpRepository {


    override suspend fun registerUser(username: String, email: String, password: String): Boolean {
        return email == "admin@gmail.com" && password == "admin123"
    }

    override suspend fun addUser(username: String, email: String, password: String) {

    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        return email == "admin@gmail.com" && password == "admin123"
    }

    override suspend fun checkUser(): Boolean {
        return false
    }

}