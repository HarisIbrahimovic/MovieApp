package com.sirahi.movieapp.repository.fake

import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.model.movie.MovieCast
import com.sirahi.movieapp.model.tv.TVDetails
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.repository.DetailsTvRepository

class FakeDetailsTVRepository:DetailsTvRepository {
    override suspend fun getTvDetails(id: Int): Response<TVDetails> {
        TODO("Not yet implemented")
    }

    override suspend fun getTvCredits(id: Int): Response<List<MovieCast>> {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(tvShow: MediaItem) {
    }

    override fun addToWatchlist(movie: MediaItem) {
    }
}