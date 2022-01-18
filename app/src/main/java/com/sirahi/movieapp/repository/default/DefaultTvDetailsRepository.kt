package com.sirahi.movieapp.repository.default

import com.bumptech.glide.load.HttpException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.data.local.dao.CastDao
import com.sirahi.movieapp.data.local.dao.TvDetailsDao
import com.sirahi.movieapp.data.remote.ApiService
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.model.tv.TVDetails
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.DetailsTvRepository
import java.io.IOException
import javax.inject.Inject

class DefaultTvDetailsRepository
@Inject constructor(
    private val apiService: ApiService,
    private val tvDetailsDao: TvDetailsDao,
    private val castDao: CastDao,
) : DetailsTvRepository {

    override suspend fun getTvDetails(id: Int): Response<TVDetails> {
        var tvDetails = tvDetailsDao.getTvDetails(id)?.toTVDetails()
        return try {
            val response = apiService.getSingleTvShow(id.toString(), ApiConstants.API_KEY)
            if (response.isSuccessful && response.body() != null) {
                tvDetailsDao.deleteTvDetails(id)
                tvDetailsDao.insertTVDetails(response.body()!!.toTVDetailsEntity())
                tvDetails = tvDetailsDao.getTvDetails(id).toTVDetails()
                Response.Success(tvDetails)
            } else Response.Error(tvDetails, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(tvDetails, "Server error occurred")
        } catch (e: IOException) {
            Response.Error(tvDetails, "Check your internet connection")
        }
    }

    override suspend fun getTvCredits(id: Int): Response<List<MovieCast>> {
        var tvStars = castDao.getCast(id)?.map { it.toMovieCast() }
        return try {
            val response = apiService.getTvCredits(id, ApiConstants.API_KEY)
            if (response.isSuccessful && response.body() != null) {
                castDao.deleteCast(id)
                castDao.insertCast(response.body()!!.cast.map { it.toMovieCastEntity(id) })
                tvStars = castDao.getCast(id)?.map { it.toMovieCast() }
                Response.Success(tvStars)
            } else Response.Error(tvStars, "Unknown error occurred")
        } catch (e: HttpException) {
            Response.Error(tvStars, "Server error occurred")
        } catch (e: IOException) {
            Response.Error(tvStars, "Check your internet connection")
        }
    }

    override fun addToFavorites(tvShow: MediaItem) {
        val auth = FirebaseAuth.getInstance()
        val dbRef =
            FirebaseDatabase.getInstance().getReference("Users").child(auth.currentUser!!.uid)
        dbRef.child("Favorites").child(tvShow.id.toString()).setValue(tvShow)
    }

    override fun addToWatchlist(movie: MediaItem) {
        val auth = FirebaseAuth.getInstance()
        val dbRef =
            FirebaseDatabase.getInstance().getReference("Users").child(auth.currentUser!!.uid)
        dbRef.child("Watchlist").child(movie.id.toString()).setValue(movie)
    }

}
