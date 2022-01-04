package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.model.Genre
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.usecases.GetPopularMoviesUseCase
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel
@Inject
constructor(
    private val repository: MenuRepository,
    private val getPopularMovies: GetPopularMoviesUseCase
) : ViewModel() {

    private val _popularMoviesData = MutableLiveData<IncomingMediaData>()
    val popularMoviesData: LiveData<IncomingMediaData> = _popularMoviesData

    private val _nowPlayingData = MutableLiveData<IncomingMediaData>()
    val nowPlayingData: LiveData<IncomingMediaData> = _nowPlayingData

    private val _popularTvData = MutableLiveData<IncomingMediaData>()
    val popularTvData: LiveData<IncomingMediaData> = _popularTvData

    private val _discoverData = MutableLiveData<IncomingMediaData>()
    val discoverData: LiveData<IncomingMediaData> = _discoverData


    private val _searchData = MutableLiveData<IncomingMediaData>()
    val searchData: LiveData<IncomingMediaData> = _searchData

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList

    private val searchList = ArrayList<MediaResult>()
    var list = ArrayList<Genre>()

    lateinit var listWatch:LiveData<ArrayList<MediaItem>>

    private var lastSelectedGenre = 0

    private val _popularMoviesFlow = MutableStateFlow<IncomingMediaData>(IncomingMediaData.Loading)
    val popularMoviesFlow: StateFlow<IncomingMediaData> = _popularMoviesFlow

    init {
        if (_popularMoviesData.value == null) {
            listWatch = repository.getFavoritesTv()
            getPopularMovies()
            getPopularTV()
            getNowPlaying()
            setGenreList()
            onSearch("Batman")
            getDiscoverData(28, "Action")
        }
        viewModelScope.launch (Dispatchers.IO){
            repository.setFavoritesTv()
        }
    }

    private fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        getPopularMovies.invoke().collect() {
            _popularMoviesFlow.value = it
        }
    }

    private fun getNowPlaying() = viewModelScope.launch(Dispatchers.IO) {
        _nowPlayingData.postValue(IncomingMediaData.Loading)
        when (val response = repository.getMovies("now_playing")) {
            is Response.Success -> _nowPlayingData.postValue(response.data?.let {
                IncomingMediaData.Success(
                    it
                )
            })
            is Response.Error -> _nowPlayingData.postValue(
                response.errorMessage?.let {
                    IncomingMediaData.Failure(
                        response.data,
                        it
                    )
                }
            )
        }
    }

    private fun setGenreList() {
        list = repository.getGenreList()
        _genreList.value = list
    }

    private fun getPopularTV() = viewModelScope.launch(Dispatchers.IO) {
        _popularTvData.postValue(IncomingMediaData.Loading)
        when (val response = repository.getTv("popular")) {
            is Response.Success -> _popularTvData.postValue(response.data?.let {
                IncomingMediaData.Success(
                    it
                )
            })
            is Response.Error -> _popularTvData.postValue(
                response.errorMessage?.let {
                    IncomingMediaData.Failure(
                        response.data,
                        it
                    )
                }
            )
        }
    }

    private fun getDiscoverData(id: Int, name: String) = viewModelScope.launch(Dispatchers.IO) {
        _discoverData.postValue(IncomingMediaData.Loading)
        when (val response = repository.discoverMovies(id, name)) {
            is Response.Success -> _discoverData.postValue(response.data?.let {
                IncomingMediaData.Success(
                    it
                )
            })
            is Response.Error -> _discoverData.postValue(
                response.errorMessage?.let {
                    IncomingMediaData.Failure(
                        response.data,
                        it
                    )
                }
            )
        }
    }

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchData.value = IncomingMediaData.Loading
        searchJob?.cancel()
        if (query == "") {
            _searchData.value = IncomingMediaData.Success(searchList)
            return
        }
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            when (val response = repository.getSearchDataMovies(query)) {
                is Response.Success -> {
                    searchList.clear()
                    response.data?.let { searchList.addAll(it) }
                }
                is Response.Error -> _searchData.postValue(
                    response.errorMessage?.let {
                        IncomingMediaData.Failure(
                            null,
                            it
                        )
                    }
                )
            }
            when (val response = repository.getSearchDataTV(query)) {
                is Response.Success -> {
                    response.data?.let { searchList.addAll(it) }
                    _searchData.postValue(IncomingMediaData.Success(searchList))
                }
                is Response.Error -> _searchData.postValue(
                    response.errorMessage?.let {
                        IncomingMediaData.Failure(
                            null,
                            it
                        )
                    }
                )
            }
        }
    }


    fun setSelectedGenre(position: Int) {
        list[lastSelectedGenre].clicked = false
        list[position].clicked = true
        lastSelectedGenre = position
        _genreList.value = list
        getDiscoverData(list[position].id, list[position].name)
    }

}