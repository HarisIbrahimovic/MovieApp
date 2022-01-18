package com.sirahi.movieapp.presentation.usecases

import androidx.lifecycle.liveData
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UserListsUseCase
@Inject
constructor(
    private val repository: MenuRepository
) {

    fun getWatchlist() = liveData(Dispatchers.IO) {
        emit(repository.getWatchlist())
    }

    fun getFavorite() = liveData(Dispatchers.IO) {
        emit(repository.getFavorites())
    }


}