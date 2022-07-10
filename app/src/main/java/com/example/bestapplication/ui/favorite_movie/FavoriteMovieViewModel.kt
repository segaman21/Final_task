package com.example.bestapplication.ui.favorite_movie

import androidx.lifecycle.*
import com.example.bestapplication.favorite_movie.entity.FavoriteMovie
import com.example.bestapplication.favorite_movie.usecase.CheckMovieInDatabaseUseCase
import com.example.bestapplication.favorite_movie.usecase.DeleteFromDataBaseUseCase
import com.example.bestapplication.favorite_movie.usecase.GetFavoriteMovieUseCase
import com.example.bestapplication.favorite_movie.usecase.InsertToDataBaseUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class FavoriteMovieViewModel @Inject constructor(
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
    private val insertToDataBaseUseCase: InsertToDataBaseUseCase,
    private val deleteFromDataBase: DeleteFromDataBaseUseCase,
    private val checkMovieInDatabaseUseCase: CheckMovieInDatabaseUseCase
) :
    ViewModel() {

    private val _isFavoriteLiveData = MutableLiveData<Boolean>()
    val isFavoriteLiveData: LiveData<Boolean> get() = _isFavoriteLiveData

    private val _favoriteMovieLiveData = MutableLiveData<List<FavoriteMovie>>()
    val favoriteMovieLiveData: LiveData<List<FavoriteMovie>> get() = _favoriteMovieLiveData

//    private val _findMovieLiveData = MutableLiveData<List<FavoriteMovie>>()
//    val findMovieLiveData: LiveData<List<FavoriteMovie>> get() = _findMovieLiveData


    @OptIn(ObsoleteCoroutinesApi::class)
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)


    @ExperimentalSerializationApi
    fun insertMovieToDatabase(movieId: Int) {
        viewModelScope.launch {
            insertToDataBaseUseCase.invoke(movieId)
        }
    }

    fun deleteMovieFromDatabase(movie: FavoriteMovie) {
        viewModelScope.launch {
            deleteFromDataBase.invoke(movie)
        }
    }

    fun checkMovieInDatabase(movieId: Int) {
        viewModelScope.launch {
            _isFavoriteLiveData.value = checkMovieInDatabaseUseCase.invoke(movieId)
        }
    }

    init {
        viewModelScope.launch {
            getFavoriteMovieUseCase.invoke().collect {
                _favoriteMovieLiveData.postValue(it)
            }
        }
    }

    @OptIn(ObsoleteCoroutinesApi::class, FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val _searchResult = queryChannel
        .asFlow()
        .debounce(500)
        .mapLatest { search ->
            getFavoriteMovieUseCase.findMovie(name = search)
        }
        .asLiveData(viewModelScope.coroutineContext)

    val searchResult: LiveData<List<FavoriteMovie>> get() = _searchResult


//
//    fun findMovieInDatabase(name: String) {
//        viewModelScope.launch {
//            getFavoriteMovieUseCase.findMovie(name = name).collect {
//                _findMovieLiveData.postValue(it)
//            }
//        }
//    }
}