package com.example.bestapplication.ui.favorite_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bestapplication.data.database.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(private val repository: FavoriteMovieRepository) :
    ViewModel() {

    val favoriteMovieLiveData: LiveData<List<MovieEntity>> = repository.allMovies.asLiveData()

    @ExperimentalSerializationApi
    fun insertToDatabase(movieId: Int) = viewModelScope.launch {
        repository.insertToDatabase(movieId)
    }
}