package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.model.Genre
import com.sirahi.movieapp.model.MediaResult
import com.sirahi.movieapp.presentation.util.Response
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel
    @Inject constructor(private val repository: MenuRepository)
    : ViewModel() {

    private val _popularMoviesData = MutableLiveData<IncomingMediaData>()
    val popularMoviesData:LiveData<IncomingMediaData> = _popularMoviesData

    private val _popularTvData = MutableLiveData<IncomingMediaData>()
    val popularTvData:LiveData<IncomingMediaData> = _popularTvData

    private val _discoverData = MutableLiveData<IncomingMediaData>()
    val discoverData:LiveData<IncomingMediaData> = _discoverData


    private val _searchData = MutableLiveData<IncomingMediaData>()
    val searchData:LiveData<IncomingMediaData> = _searchData

    private val searchList=ArrayList<MediaResult>()


    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList:LiveData<List<Genre>> = _genreList

    var list= ArrayList<Genre>()


    private var lastSelectedGenre=0

    init {
        if(_popularMoviesData.value==null){
            getPopularMovies()
            getPopularTV()
            setGenreList()
            getDiscoverData(28,"Action")
        }
    }

    private fun setGenreList() {
        list= repository.getGenreList()
        _genreList.value=list
    }

    private fun getPopularTV() = viewModelScope.launch(Dispatchers.IO){
        _popularTvData.postValue(IncomingMediaData.Loading)
        when(val response= repository.getTv("popular")){
            is Response.Success-> _popularTvData.postValue(IncomingMediaData.Success(response.data!!))
            is Response.Error-> _popularTvData.postValue(IncomingMediaData.Failure(response.data,response.errorMessage!!))
        }
    }

    private fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO){
        _popularMoviesData.postValue(IncomingMediaData.Loading)
        when(val response= repository.getMovies("popular")){
            is Response.Success-> _popularMoviesData.postValue(IncomingMediaData.Success(response.data!!))
            is Response.Error-> _popularMoviesData.postValue(IncomingMediaData.Failure(response.data,response.errorMessage!!))
        }
    }

    private fun getDiscoverData(id: Int,name:String) = viewModelScope.launch(Dispatchers.IO){
        _discoverData.postValue(IncomingMediaData.Loading)
        when(val response= repository.discoverMovies(id,name)){
            is Response.Success-> _discoverData.postValue(IncomingMediaData.Success(response.data!!))
            is Response.Error-> _discoverData.postValue(IncomingMediaData.Failure(response.data,response.errorMessage!!))
        }
    }

    private var searchJob:Job?=null

    fun onSearch(query:String){
        _searchData.value = IncomingMediaData.Loading
        searchJob?.cancel()
        if(query=="") {
            _searchData.value=IncomingMediaData.Success(searchList)
            return
        }
        searchJob=viewModelScope.launch (Dispatchers.IO){
            delay(500)
            when(val response = repository.getSearchDataMovies(query)){
                is Response.Success -> {
                    searchList.clear()
                    searchList.addAll(response.data!!)
                }
                is Response.Error -> _searchData.postValue(IncomingMediaData.Failure(null, response.errorMessage!!))
            }
            when(val response = repository.getSearchDataTV(query)){
                is Response.Success -> {
                    searchList.addAll(response.data!!)
                    _searchData.postValue(IncomingMediaData.Success(searchList))
                }
                is Response.Error -> _searchData.postValue(IncomingMediaData.Failure(null, response.errorMessage!!))
            }
        }
    }



    fun setSelectedGenre(position:Int){
        list[lastSelectedGenre].clicked=false
        list[position].clicked =true
        lastSelectedGenre=position
        _genreList.value=list
        getDiscoverData(list[position].id,list[position].name)
    }

}