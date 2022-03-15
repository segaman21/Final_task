package com.example.bestapplication.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestapplication.data.model.*
import com.example.bestapplication.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MoviesRepository) :
    ViewModel() {
    private val _moviesLiveData = MutableLiveData<MovieFull>()
    val moviesLiveData: LiveData<MovieFull> get() = _moviesLiveData
    private val _actorsLiveData = MutableLiveData<List<Actor>>()
    val actorsLiveData: LiveData<List<Actor>> get() = _actorsLiveData

    fun getMovies(movieId: Int) {
        viewModelScope.launch {
            _moviesLiveData.value = repository.getMovie(movieId)
        }
    }

    fun getActors(movieId: Int) {
        viewModelScope.launch {
            _actorsLiveData.value = repository.getActors(movieId)
        }
    }
}