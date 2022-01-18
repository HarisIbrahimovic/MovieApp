package com.sirahi.movieapp.presentation.usecases.details.tv

import com.sirahi.movieapp.model.tv.TVDetails
import com.sirahi.movieapp.repository.DetailsTvRepository
import com.sirahi.movieapp.repository.default.DefaultTvDetailsRepository
import javax.inject.Inject

class GetTvDetailsUseCase
@Inject
constructor(private val repository: DetailsTvRepository) {

    suspend fun invoke(id: Int): TVDetails {
        val response = repository.getTvDetails(id)
        return response.data?: TVDetails()
    }

}