package com.example.bestapplication.ui.favorite_movie

import androidx.lifecycle.*
import com.example.bestapplication.favorite_movie.entity.MovieDatabaseEntity
import com.example.bestapplication.favorite_movie.usecase.CheckMovieInDatabaseUseCase
import com.example.bestapplication.favorite_movie.usecase.DeleteFromDataBase
import com.example.bestapplication.favorite_movie.usecase.GetFavoriteMovieUseCase
import com.example.bestapplication.favorite_movie.usecase.InsertToDataBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
    private val insertToDataBaseUseCase: InsertToDataBaseUseCase,
    private val deleteFromDataBase: DeleteFromDataBase,
    private val checkMovieInDatabaseUseCase: CheckMovieInDatabaseUseCase
) :
    ViewModel() {

    private val _isFavoriteLiveData = MutableLiveData<Boolean>()
    val isFavoriteLiveData: LiveData<Boolean> get() = _isFavoriteLiveData

    private val _favoriteMovieLiveData = MutableLiveData<List<MovieDatabaseEntity>>()
    val favoriteMovieLiveData: LiveData<List<MovieDatabaseEntity>> get() = _favoriteMovieLiveData


    @ExperimentalSerializationApi
    fun insertMovieToDatabase(movieId: Int) {
        viewModelScope.launch {
            insertToDataBaseUseCase.invoke(movieId)
        }
    }

    fun deleteMovieFromDatabase(movie: MovieDatabaseEntity) {
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
}