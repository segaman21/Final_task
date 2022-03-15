package com.example.bestapplication.ui.favorite_movie

import androidx.lifecycle.*
import com.example.bestapplication.data.database.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(private val repository: FavoriteMovieRepository) :
    ViewModel() {
    val favoriteMovieLiveData: LiveData<List<MovieEntity>> = repository.allMovies.asLiveData()
    private val _isFavoriteLiveData = MutableLiveData<Boolean>()
    val isFavoriteLiveData: LiveData<Boolean> get() = _isFavoriteLiveData

    @ExperimentalSerializationApi
    fun insertMovieToDatabase(movieId: Int) {
        viewModelScope.launch {
            repository.insertToDatabase(movieId)
        }
    }

    fun deleteMovieFromDatabase(movie: MovieEntity) {
        viewModelScope.launch {
            repository.deleteFromDatabase(movie)
        }
    }

    fun checkMovieInDatabase(movieId: Int) {
        viewModelScope.launch {
            _isFavoriteLiveData.value = repository.checkMovieInDatabase(movieId)
        }
    }
}