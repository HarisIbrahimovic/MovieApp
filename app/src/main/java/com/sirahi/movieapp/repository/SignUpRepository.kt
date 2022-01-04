package com.sirahi.movieapp.repository

import androidx.lifecycle.MutableLiveData
import com.sirahi.movieapp.presentation.util.RegistrationStatus

interface SignUpRepository {
    suspend fun registerUser(username: String, email: String, password: String): Boolean
    suspend fun addUser(username: String, email: String, password: String)
    suspend fun loginUser(email: String, password: String): Boolean
    suspend fun checkUser(): Boolean
}