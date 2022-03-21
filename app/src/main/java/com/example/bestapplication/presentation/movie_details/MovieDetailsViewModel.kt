package com.example.bestapplication.presentation.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import com.example.data.MoviesRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repositoryImp: MoviesRepositoryImp) :
    ViewModel() {
    private val _moviesLiveData = MutableLiveData<MovieFullApi>()
    val moviesLiveData: LiveData<MovieFullApi> get() = _moviesLiveData
    private val _actorsLiveData = MutableLiveData<List<Actor>>()
    val actorsLiveData: LiveData<List<Actor>> get() = _actorsLiveData

    fun getMovies(movieId: Int) {
        viewModelScope.launch {
            _moviesLiveData.value = repositoryImp.getMovie(movieId)
        }
    }

    fun getActors(movieId: Int) {
        viewModelScope.launch {
            _actorsLiveData.value = repositoryImp.getActors(movieId)
        }
    }
}