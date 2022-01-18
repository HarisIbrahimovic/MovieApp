package com.sirahi.movieapp.repository

interface SignUpRepository {
    suspend fun registerUser(username: String, email: String, password: String): Boolean
    suspend fun addUser(username: String, email: String, password: String)
    suspend fun loginUser(email: String, password: String): Boolean
    suspend fun checkUser(): Boolean
}