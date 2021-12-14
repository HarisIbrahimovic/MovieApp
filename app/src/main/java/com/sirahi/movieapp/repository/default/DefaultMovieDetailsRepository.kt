package com.sirahi.movieapp.repository.default

import com.bumptech.glide.load.HttpException
import com.sirahi.movieapp.data.local.LocalDatabase
import com.sirahi.movieapp.data.local.dao.CastDao
import com.sirahi.movieapp.data.local.dao.MovieDetailsDao
import com.sirahi.movieapp.data.remote.RetrofitInstance
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.model.movie.MovieDetails
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.DetailMovieRepository
import java.io.IOException

class DefaultMovieDetailsRepository: DetailMovieRepository {
    private val apiService = RetrofitInstance.api
    private val movieDetailsDao:MovieDetailsDao = LocalDatabase.getInstance()!!.movieDetailsDao()
    private val castDao:CastDao = LocalDatabase.getInstance()!!.castDao()

    override suspend fun getMovieDetails(id: Int): Response<MovieDetails> {
        var detailsResult:MovieDetails = movieDetailsDao.getMovieData(id).toMovieDetails()
        return try {
            val response = apiService.getSingleMovie(id.toString(),ApiConstants.API_KEY)
            val result = response.body()?.toMovieDetailsEntity()
            if(result!=null){
                movieDetailsDao.deleteMovie(id)
                movieDetailsDao.insertMovieDetails(result)
                detailsResult=movieDetailsDao.getMovieData(id).toMovieDetails()
                Response.Success(detailsResult)
            } else Response.Error(detailsResult,"Unknown error occurred")
        }catch (e:HttpException){
            Response.Error(detailsResult,"Server error occurred")
        }catch (e:IOException){
            Response.Error(detailsResult,"Check your internet connection")
        }
    }

    override suspend fun getMovieCredits(id: Int): Response<List<MovieCast>> {

        var cast = castDao.getCast(id).map { it.toMovieCast() }

        return try {
            val response = apiService.getMovieCredits(id.toString(),ApiConstants.API_KEY)
            val result = response.body()?.cast
            if (result != null) {
                castDao.deleteCast(id)
                result.map { it.checkNull() }
                castDao.insertCast(result.map { it.toMovieCastEntity(id) })
                cast = castDao.getCast(id).map { it.toMovieCast() }
                Response.Success(cast)
            }else Response.Error(cast,"Unknown error occurred")

            }catch (e:HttpException){
                Response.Error(cast,"Server error occurred")
            }catch (e:IOException){
            Response.Error(cast,"Check your internet connection")
        }
    }


}