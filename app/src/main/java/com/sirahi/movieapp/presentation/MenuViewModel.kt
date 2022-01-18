package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.model.Genre
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.usecases.*
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel
@Inject
constructor(
    userListsUseCase: UserListsUseCase,
    getMoviesByCategory: GetMoviesByCategoryUseCase,
    getTvByCategoryUseCase: GetTvByCategoryUseCase,
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,
    private val searchUseCase: SearchUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {


    var userWatchList = userListsUseCase.getWatchlist()
    var userFavList = userListsUseCase.getFavorite()

    var popularMoviesData = getMoviesByCategory.invoke("popular")
    val popularTvData = getTvByCategoryUseCase.invoke("popular")
    var nowPlayingData = getMoviesByCategory.invoke("now_playing")

    private val _discoverData = MutableLiveData<IncomingMediaData>()
    var discoverData: LiveData<IncomingMediaData> = _discoverData

    private val _searchData = MutableLiveData<IncomingMediaData>()
    var searchData: LiveData<IncomingMediaData> = _searchData

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList

    private var currentList = ArrayList<MediaResult>()
    private var searchJob: Job? = null

    init {
        _genreList.postValue(getGenresUseCase.list)
        onSearch("Batman")
        getDiscoverData(28, "Action")
    }

    private fun getDiscoverData(id: Int, name: String) = viewModelScope.launch(Dispatchers.IO) {
        _discoverData.postValue(IncomingMediaData.Loading)
        discoverMoviesUseCase.invoke(id, name).collect {
            _discoverData.postValue(it)
        }
    }

    fun onSearch(query: String) {
        _searchData.value = IncomingMediaData.Loading
        searchJob?.cancel()
        if (query == "") {
            _searchData.value = IncomingMediaData.Success(currentList)
            return
        }
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            searchUseCase.invoke(query).collect {
                _searchData.postValue(it)
                it.data?.let { list ->
                    currentList = list as ArrayList<MediaResult>
                }
            }
        }
    }

    fun setSelectedGenre(position: Int) = viewModelScope.launch(Dispatchers.IO) {
        getGenresUseCase.selectGenre(position).collect {
            getDiscoverData(it.id, it.name)
            _genreList.postValue(getGenresUseCase.list)
        }
    }

    fun signOut() {

    }

}