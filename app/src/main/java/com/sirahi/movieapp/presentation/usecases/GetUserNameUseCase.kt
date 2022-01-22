package com.sirahi.movieapp.presentation.usecases

import com.sirahi.movieapp.repository.MenuRepository
import javax.inject.Inject

class GetUserNameUseCase@Inject constructor(private val repository: MenuRepository) {

    suspend fun invoke():String{
        return repository.getUserName()
    }
}