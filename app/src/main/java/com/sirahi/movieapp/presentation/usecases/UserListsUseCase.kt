package com.sirahi.movieapp.presentation.usecases

import androidx.lifecycle.liveData
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UserListsUseCase
@Inject
constructor(
    private val repository: MenuRepository
) {

    suspend fun getWatchlist(): ArrayList<MediaItem> {
       return repository.getWatchlist()
    }

    suspend fun getFavorite(): ArrayList<MediaItem> {
        return repository.getFavorites()
    }


}