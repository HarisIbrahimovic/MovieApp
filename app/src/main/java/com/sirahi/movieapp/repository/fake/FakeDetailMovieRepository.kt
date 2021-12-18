package com.sirahi.movieapp.repository.fake

import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.model.movie.MovieDetails
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.DetailMovieRepository

class FakeDetailMovieRepository:DetailMovieRepository {
    override suspend fun getMovieDetails(id: Int): Response<MovieDetails> {
        val movieDetails = MovieDetails("16-12-2021",7.8,"Venom: Let There Be Carnage",148,"/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg","overview","/eENEf62tMXbhyVvdcXlnQz2wcuT.jpg")
        return Response.Success(movieDetails)
    }

    override suspend fun getMovieCredits(id: Int): Response<List<MovieCast>> {
        val list = ArrayList<MovieCast>()
        list.add(MovieCast("Tom Hardy","Eddie Brock/ Venom",1,"/9bXUMLOBCcnhcUGtfw0pdqbvpiH.jpg"))
        return Response.Success(list)
    }
}