package com.sirahi.movieapp.presentation

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.data.firebase.MediaItem
import com.sirahi.movieapp.model.Genre
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.usecases.*
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaDataDelegates
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
    private val getMoviesByCategory: GetMoviesByCategoryUseCase,
    private val getTvByCategoryUseCase: GetTvByCategoryUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,
    private val searchUseCase: SearchUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    var userWatchList = ArrayList<MediaItem>()
    var userFavList = ArrayList<MediaItem>()

    var userName = ObservableField("")

    var nowPlayingMoviesObservable = IncomingMediaDataDelegates()
    var popularMoviesObservable = IncomingMediaDataDelegates()
    var popularTvObservable = IncomingMediaDataDelegates()


    private val _discoverData = MutableLiveData<IncomingMediaData>()
    var discoverData: LiveData<IncomingMediaData> = _discoverData

    private val _searchData = MutableLiveData<IncomingMediaData>()
    var searchData: LiveData<IncomingMediaData> = _searchData

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList

    private var currentList = ArrayList<MediaResult>()
    private var searchJob: Job? = null

    init {
        viewModelScope.launch (Dispatchers.IO){
            setPopularTvObservable()
            setPopularMoviesObservable()
            userWatchList = userListsUseCase.getWatchlist()
            userFavList = userListsUseCase.getFavorite()
            nowPlayingMoviesObservable.setValues(getMoviesByCategory.invoke("now_playing"))
            userName.set(getUserNameUseCase.invoke())
        }
        _genreList.postValue(getGenresUseCase.list)
        getDiscoverData(28, "Action")
        onSearch("Batman")
    }

    private suspend fun setPopularMoviesObservable() {
        popularMoviesObservable.loadingState=true
        popularMoviesObservable.setValues(getMoviesByCategory.invoke("popular"))
        popularMoviesObservable.loadingState=false
    }

    private suspend fun setPopularTvObservable() {
        popularTvObservable.loadingState=true
        popularTvObservable.setValues(getTvByCategoryUseCase.invoke("popular"))
        popularTvObservable.loadingState=false
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