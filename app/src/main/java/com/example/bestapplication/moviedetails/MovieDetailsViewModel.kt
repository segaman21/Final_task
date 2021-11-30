package com.example.bestapplication.moviedetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestapplication.data.model.*
import com.example.bestapplication.data.network.MoviesRepository
import kotlinx.coroutines.*


class MovieDetailsViewModel : ViewModel() {
    private val _moviesLiveData = MutableLiveData<MovieFull>()
    val moviesLiveData: LiveData<MovieFull> get() = _moviesLiveData
    private val _actorsLiveData = MutableLiveData<List<Actor>>()
    val actorsLiveData: LiveData<List<Actor>> get() = _actorsLiveData
    private val repository = MoviesRepository()

    fun getMovies(context: Context, movieId: Int) {
        viewModelScope.launch {
            _moviesLiveData.value = repository.getMovie(context, movieId)
        }
    }

    fun getActors(context: Context, movieId: Int) {
        viewModelScope.launch {
            _actorsLiveData.value = repository.getActors(context, movieId)
        }
    }

}