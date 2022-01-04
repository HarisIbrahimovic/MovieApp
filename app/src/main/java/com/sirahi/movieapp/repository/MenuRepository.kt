package com.sirahi.movieapp.repository

import androidx.lifecycle.LiveData
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.data.firebase.Rating
import com.sirahi.movieapp.model.Genre
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.Response

interface MenuRepository {
    suspend fun getMovies(category: String): Response<List<MediaResult>?>
    suspend fun getTv(category: String): Response<List<MediaResult>?>
    suspend fun discoverMovies(id: Int, name: String): Response<List<MediaResult>?>
    suspend fun getSearchDataMovies(query: String): Response<List<MediaResult>?>
    suspend fun getSearchDataTV(query: String): Response<List<MediaResult>?>
    fun getGenreList(): ArrayList<Genre>
    suspend fun getWatchlistMovies(): LiveData<ArrayList<MediaItem>>
    suspend fun getFavoritesMovies(): LiveData<ArrayList<MediaItem>>
    suspend fun getWatchlistTv(): LiveData<ArrayList<MediaItem>>
    fun getFavoritesTv(): LiveData<ArrayList<MediaItem>>
    suspend fun setFavoritesTv()


}