package com.sirahi.movieapp.repository.default

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.data.local.dao.MediaResultDao
import com.sirahi.movieapp.data.remote.ApiService
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.model.Genre
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.CancellationException
import java.util.concurrent.Flow
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class DefaultMenuRepository @Inject constructor(
    private val mediaDao: MediaResultDao,
    private val apiService: ApiService
) : MenuRepository {

    private val watchlistMovies = MutableLiveData<ArrayList<MediaItem>>()
    private val watchlistTv = MutableLiveData<ArrayList<MediaItem>>()
    private val favoriteMovies = MutableLiveData<ArrayList<MediaItem>>()
    private val favoriteTv = MutableLiveData<ArrayList<MediaItem>>()
    private val auth = FirebaseAuth.getInstance()


    override suspend fun getMovies(category: String): Response<List<MediaResult>?> {
        var mediaResult = mediaDao.getMediaResult("movie").map { it.toMediaResult() }
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
                mediaResult = mediaDao.getMediaResult("movie").map { it.toMediaResult() }
                Response.Success(mediaResult)
            } else Response.Error(listMediaResult, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(mediaResult, "Network error occurred")
        } catch (e: IOException) {
            Response.Error(mediaResult, "Check your internet connection")
        }
    }

    override suspend fun getTv(category: String): Response<List<MediaResult>?> {
        var mediaResult = mediaDao.getMediaResult("TV").map { it.toMediaResult() }
        return try {
            val remoteMediaResult = apiService.getTV(category, ApiConstants.API_KEY)
            val listMediaResult = remoteMediaResult.body()?.results
            if (listMediaResult != null) {
                mediaDao.deleteAllTV()
                listMediaResult.map { it.checkNull() }
                mediaDao.insertMediaResult(listMediaResult.map {
                    it.toMediaResultEntity(
                        "popular",
                        "TV"
                    )
                })
                mediaResult = mediaDao.getMediaResult("TV").map { it.toMediaResult() }
                Response.Success(mediaResult)
            } else
                Response.Error(mediaResult, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(mediaResult, "Network error occurred")
        } catch (e: IOException) {
            Response.Error(mediaResult, "Check your internet connection")
        }
    }


    override suspend fun discoverMovies(id: Int, name: String): Response<List<MediaResult>?> {
        var mediaResult = mediaDao.getMediaResult(name).map { it.toMediaResult() }
        return try {
            val remoteMediaResult = apiService.discoverMovies(id, ApiConstants.API_KEY)
            val listMediaResult = remoteMediaResult.body()?.results
            if (listMediaResult != null) {
                mediaDao.deleteAllGenre(name)
                listMediaResult.map { it.checkPosterPath() }
                mediaDao.insertMediaResult(listMediaResult.map {
                    it.toMediaResultEntity(
                        "selection",
                        name
                    )
                })
                mediaResult = mediaDao.getMediaResult(name).map { it.toMediaResult() }
                Response.Success(mediaResult)
            } else Response.Error(mediaResult, "Unknown error occurred")
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
                remoteMediaResult.body()?.results?.map { it.checkPosterPath() }
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
                remoteMediaResult.body()?.results?.map { it.checkNull() }
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

    override suspend fun getWatchlistMovies(): LiveData<ArrayList<MediaItem>> {
        val arrayList = ArrayList<MediaItem>()
        val snapshotList = FirebaseDatabase.getInstance()
            .getReference("Users")
            .child(auth.currentUser!!.uid)
            .child("WatchlistMovies")
            .get()
            .await()
        for (snapshot in snapshotList.children)
            snapshot.getValue(MediaItem::class.java)?.let { arrayList.add(it) }
        watchlistMovies.value = arrayList
        return watchlistMovies
    }

    override suspend fun getFavoritesMovies(): LiveData<ArrayList<MediaItem>> {
        val arrayList = ArrayList<MediaItem>()
        val snapshotList = FirebaseDatabase.getInstance()
            .getReference("Users")
            .child(auth.currentUser!!.uid)
            .child("FavoritesMovies")
            .get()
            .await()
        for (snapshot in snapshotList.children)
            snapshot.getValue(MediaItem::class.java)?.let { arrayList.add(it) }
        favoriteMovies.value = arrayList
        return favoriteMovies
    }

    override suspend fun getWatchlistTv(): LiveData<ArrayList<MediaItem>> {
        val arrayList = ArrayList<MediaItem>()
        val snapshotList = FirebaseDatabase.getInstance()
            .getReference("Users")
            .child(auth.currentUser!!.uid)
            .child("WatchlistTV")
            .get()
            .await()
        for (snapshot in snapshotList.children)
            snapshot.getValue(MediaItem::class.java)?.let { arrayList.add(it) }
        watchlistTv.value = arrayList
        return watchlistTv
    }

    override fun getFavoritesTv(): LiveData<ArrayList<MediaItem>> = favoriteTv


    override suspend fun setFavoritesTv() {
        val arrayList = ArrayList<MediaItem>()
        try {
            val snapshotList = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(auth.currentUser!!.uid)
                .child("FavoritesTv")
                .get()
                .await()
            for (snapshot in snapshotList.children)
                snapshot.getValue(MediaItem::class.java)?.let { arrayList.add(it) }
            favoriteTv.postValue(arrayList)
        }catch (e:Exception){

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