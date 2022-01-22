package com.sirahi.movieapp.presentation.usecases.details.movies

import com.sirahi.movieapp.model.movie.MovieDetails
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.DetailMovieRepository
import com.sirahi.movieapp.repository.MenuRepository
import com.sirahi.movieapp.repository.SignUpRepository
import com.sirahi.movieapp.repository.default.DefaultMovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase
@Inject
constructor(private val repository: DetailMovieRepository) {

    suspend fun invoke(id: Int): MovieDetails? {
        return repository.getMovieDetails(id).data
    }


}