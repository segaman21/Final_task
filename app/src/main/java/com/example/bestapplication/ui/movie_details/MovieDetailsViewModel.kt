package com.example.bestapplication.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestapplication.movie_details.entity.Actor
import com.example.bestapplication.movie_details.entity.MovieFull
import com.example.bestapplication.movie_details.usecase.GetActorUseCase
import com.example.bestapplication.movie_details.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val getActorUseCase: GetActorUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) :
    ViewModel() {
    private val _moviesLiveData = MutableLiveData<MovieFull>()
    val moviesLiveData: LiveData<MovieFull> get() = _moviesLiveData
    private val _actorsLiveData = MutableLiveData<List<Actor>>()
    val actorsLiveData: LiveData<List<Actor>> get() = _actorsLiveData

    fun getFullMovies(movieId: Int) {
        viewModelScope.launch {
            _moviesLiveData.value = getMovieDetailsUseCase.invoke(movieId)
        }
    }

    fun getActors(movieId: Int) {
        viewModelScope.launch {
            _actorsLiveData.value = getActorUseCase.invoke(movieId)
        }
    }


}