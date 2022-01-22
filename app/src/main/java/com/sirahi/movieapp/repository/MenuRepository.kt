package com.sirahi.movieapp.repository

import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.model.Genre
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.Response

interface MenuRepository {
    suspend fun getMovies(category: String): Response<List<MediaResult>>
    suspend fun getTv(category: String): Response<List<MediaResult>>
    suspend fun discoverMovies(id: Int, name: String): Response<List<MediaResult>>
    suspend fun getSearchDataMovies(query: String): Response<List<MediaResult>?>
    suspend fun getSearchDataTV(query: String): Response<List<MediaResult>?>
    suspend fun getWatchlist(): ArrayList<MediaItem>
    suspend fun getFavorites(): ArrayList<MediaItem>
    suspend fun getUserName(): String
    fun getGenreList(): ArrayList<Genre>


}