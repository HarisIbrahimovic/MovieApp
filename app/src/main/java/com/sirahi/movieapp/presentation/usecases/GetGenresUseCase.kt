package com.sirahi.movieapp.presentation.usecases

import com.sirahi.movieapp.model.Genre
import com.sirahi.movieapp.repository.MenuRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGenresUseCase
@Inject
constructor(repository: MenuRepository) {

    var list = ArrayList<Genre>()
    private var lastSelectedGenre = 0

    init {
        list = repository.getGenreList()
    }

    fun selectGenre(position:Int)= flow{
        list[lastSelectedGenre].clicked = false
        list[position].clicked = true
        lastSelectedGenre = position
        emit(list[position])
    }
}