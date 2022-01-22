package com.sirahi.movieapp.repository.default

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.data.local.dao.MediaResultDao
import com.sirahi.movieapp.data.remote.ApiService
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.model.Genre
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.tasks.await
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DefaultMenuRepository @Inject constructor(
    private val mediaDao: MediaResultDao,
    private val apiService: ApiService
) : MenuRepository {

    private val auth = FirebaseAuth.getInstance()


    override suspend fun getMovies(category: String): Response<List<MediaResult>> {
        var mediaResult = mediaDao.getMediaResult("movie")?.map { it.toMediaResult() }
        return try {
            val remoteMediaResult = apiService.getMovies(category, ApiConstants.API_KEY)
            val listMediaResult = remoteMediaResult.body()?.results
            if (listMediaResult != null) {
                mediaDao.deleteAllPMovies()
                mediaDao.insertMediaResult(listMediaResult.map {
                    it.toMediaResultEntity(
                        category,
                        "movie"
                    )
                })
                mediaResult = mediaDao.getMediaResult("movie")?.map { it.toMediaResult() }
                Response.Success(mediaResult?:ArrayList())
            } else Response.Error(listMediaResult, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(mediaResult, "Network error occurred")
        } catch (e: IOException) {
            Response.Error(mediaResult, "Check your internet connection")
        }
    }

    override suspend fun getTv(category: String): Response<List<MediaResult>> {
        var mediaResult = mediaDao.getMediaResult("TV")?.map { it.toMediaResult() }
        return try {
            val remoteMediaResult = apiService.getTV(category, ApiConstants.API_KEY)
            val listMediaResult = remoteMediaResult.body()?.results
            if (listMediaResult != null) {
                mediaDao.deleteAllTV()
                mediaDao.insertMediaResult(listMediaResult.map {
                    it.toMediaResultEntity(
                        "popular",
                        "TV"
                    )
                })
                mediaResult = mediaDao.getMediaResult("TV")?.map { it.toMediaResult() }
                Response.Success(mediaResult?:ArrayList())
            } else
                Response.Error(mediaResult, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(mediaResult, "Network error occurred")
        } catch (e: IOException) {
            Response.Error(mediaResult, "Check your internet connection")
        }
    }


    override suspend fun discoverMovies(id: Int, name: String): Response<List<MediaResult>> {
        var mediaResult = mediaDao.getMediaResult(name)?.map { it.toMediaResult() }
        return try {
            val remoteMediaResult = apiService.discoverMovies(id, ApiConstants.API_KEY)
            val listMediaResult = remoteMediaResult.body()?.results
            if (listMediaResult != null) {
                mediaDao.deleteAllGenre(name)
                mediaDao.insertMediaResult(listMediaResult.map {
                    it.toMediaResultEntity(
                        "selection",
                        name
                    )
                })
                mediaResult = mediaDao.getMediaResult(name)?.map { it.toMediaResult() }
                Response.Success(mediaResult?:ArrayList())
            } else Response.Error(mediaResult?:ArrayList(), "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(mediaResult, "Server error occurred")
        } catch (e: IOException) {
            Response.Error(mediaResult, "Check your internet connection")
        }
    }

    override suspend fun getSearchDataMovies(query: String): Response<List<MediaResult>?> {
        return try {
            val remoteMediaResult =
                apiService.getSearchDataMovie("movie", ApiConstants.API_KEY, query)
            if (remoteMediaResult.body() != null) {
                val listMediaResult =
                    remoteMediaResult.body()?.results?.map { it.toMediaResult("movie") }
                Response.Success(listMediaResult)
            } else Response.Error(null, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(null, "Server error occurred")
        } catch (e: IOException) {
            Response.Error(null, "Check your internet connection")
        }
    }

    override suspend fun getSearchDataTV(query: String): Response<List<MediaResult>?> {
        return try {
            val remoteMediaResult = apiService.getSearchDataTv("tv", ApiConstants.API_KEY, query)
            if (remoteMediaResult.body() != null) {
                val listMediaResult =
                    remoteMediaResult.body()?.results?.map { it.toMediaResult("tv") }
                Response.Success(listMediaResult)
            } else Response.Error(null, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(null, "Server error occurred")
        } catch (e: IOException) {
            Response.Error(null, "Check your internet connection")
        }
    }


    override suspend fun getWatchlist(): ArrayList<MediaItem> {
        val arrayList = ArrayList<MediaItem>()
        return try {
            val snapshotList = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(auth.currentUser?.uid?:"")
                .child("Watchlist")
                .get()
                .await()
            for (snapshot in snapshotList.children)
                snapshot.getValue(MediaItem::class.java)?.let { arrayList.add(it) }
            arrayList
        } catch (e: Exception) {
            arrayList
        }
    }

    override suspend fun getFavorites(): ArrayList<MediaItem> {
        val arrayList = ArrayList<MediaItem>()
        return try {
            val snapshotList = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(auth.currentUser?.uid?:"")
                .child("Favorites")
                .get()
                .await()
            for (snapshot in snapshotList.children)
                snapshot.getValue(MediaItem::class.java)?.let { arrayList.add(it) }
            arrayList
        } catch (e: Exception) {
            arrayList
        }
    }

    override suspend fun getUserName(): String {
        return try {
            val snapshot = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(auth.currentUser?.uid?:"")
                .child("username")
                .get()
                .await()
            snapshot.getValue(String::class.java)?:"nsad"
        } catch (e: Exception) {
           ""
        }
    }


    override fun getGenreList(): ArrayList<Genre> {
        val list = ArrayList<Genre>()
        list.add(Genre(28, "Action", true))
        list.add(Genre(12, "Adventure"))
        list.add(Genre(16, "Animation"))
        list.add(Genre(35, "Comedy"))
        list.add(Genre(80, "Crime"))
        list.add(Genre(99, "Documentary"))
        list.add(Genre(18, "Drama"))
        list.add(Genre(10751, "Family"))
        list.add(Genre(14, "Fantasy"))
        list.add(Genre(36, "History"))
        list.add(Genre(27, "Horror"))
        list.add(Genre(10402, "Music"))
        list.add(Genre(9648, "Mystery"))
        list.add(Genre(10749, "Romance"))
        list.add(Genre(878, "Science Fiction"))
        list.add(Genre(10770, "TV Movie"))
        list.add(Genre(53, "Thriller"))
        list.add(Genre(10752, "War"))
        list.add(Genre(37, "Western"))
        return list
    }

}