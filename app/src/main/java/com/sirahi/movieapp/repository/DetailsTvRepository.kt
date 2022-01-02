package com.sirahi.movieapp.repository

import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.model.tv.TVDetails
import com.sirahi.movieapp.presentation.util.Response

interface DetailsTvRepository {
    suspend fun getTvDetails(id:Int): Response<TVDetails>
    suspend fun getTvCredits(id: Int): Response<List<MovieCast>>
}