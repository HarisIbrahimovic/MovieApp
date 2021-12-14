package com.sirahi.movieapp.repository

import androidx.lifecycle.MutableLiveData
import com.sirahi.movieapp.presentation.util.RegistrationStatus

interface SignUpRepository {
    fun registerUser(username:String, email:String, password:String)
    fun getRegistrationLiveData(): MutableLiveData<RegistrationStatus>
    fun addUser(username: String,email: String,password: String)
    fun loginUser(email: String,password: String)
    fun checkUser()
}