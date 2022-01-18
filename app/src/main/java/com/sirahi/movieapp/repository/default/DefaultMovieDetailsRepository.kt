package com.sirahi.movieapp.repository.default

import com.bumptech.glide.load.HttpException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.data.local.dao.CastDao
import com.sirahi.movieapp.data.local.dao.MovieDetailsDao
import com.sirahi.movieapp.data.remote.ApiService
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.model.movie.MovieDetails
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.DetailMovieRepository
import java.io.IOException
import javax.inject.Inject

class DefaultMovieDetailsRepository
@Inject
constructor(
    private val movieDetailsDao: MovieDetailsDao,
    private val castDao: CastDao,
    private val apiService: ApiService
) : DetailMovieRepository {

    override suspend fun getMovieDetails(id: Int): Response<MovieDetails> {
        var detailsResult: MovieDetails = movieDetailsDao.getMovieData(id)?.toMovieDetails()
        return try {
            val response = apiService.getSingleMovie(id.toString(), ApiConstants.API_KEY)
            val result = response.body()?.toMovieDetailsEntity()
            if (result != null) {
                movieDetailsDao.deleteMovie(id)
                movieDetailsDao.insertMovieDetails(result)
                detailsResult = movieDetailsDao.getMovieData(id).toMovieDetails()
                Response.Success(detailsResult)
            } else Response.Error(detailsResult, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(detailsResult, "Server error occurred")
        } catch (e: IOException) {
            Response.Error(detailsResult, "Check your internet connection")
        }
    }

    override suspend fun getMovieCredits(id: Int): Response<List<MovieCast>> {

        var cast = castDao.getCast(id).map { it.toMovieCast() }

        return try {
            val response = apiService.getMovieCredits(id.toString(), ApiConstants.API_KEY)
            val result = response.body()?.cast
            if (result != null) {
                castDao.deleteCast(id)
                castDao.insertCast(result.map { it.toMovieCastEntity(id) })
                cast = castDao.getCast(id).map { it.toMovieCast() }
                Response.Success(cast)
            } else Response.Error(cast, "Unknown error occurred")

        } catch (e: HttpException) {
            Response.Error(cast, "Server error occurred")
        } catch (e: IOException) {
            Response.Error(cast, "Check your internet connection")
        }
    }

    override fun addToFavorites(mediaItem: MediaItem) {
        val auth = FirebaseAuth.getInstance()
        val dbRef =
            FirebaseDatabase.getInstance().getReference("Users").child(auth.currentUser!!.uid)
        dbRef.child("Favorites").child(mediaItem.id.toString()).setValue(mediaItem)
    }

    override fun addToWatchlist(movie: MediaItem) {
        val auth = FirebaseAuth.getInstance()
        val dbRef =
            FirebaseDatabase.getInstance().getReference("Users").child(auth.currentUser!!.uid)
        dbRef.child("Watchlist").child(movie.id.toString()).setValue(movie)
    }


}