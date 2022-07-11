package com.example.bestapplication.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestapplication.R
import com.example.bestapplication.movie_details.entity.Actor
import com.example.bestapplication.movie_details.entity.MovieFull
import com.example.bestapplication.movie_details.usecase.GetActorUseCase
import com.example.bestapplication.movie_details.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val getActorUseCase: GetActorUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _moviesLiveData = MutableLiveData<MovieFull>()
    val moviesLiveData: LiveData<MovieFull> get() = _moviesLiveData
    private val _actorsLiveData = MutableLiveData<List<Actor>>()
    val actorsLiveData: LiveData<List<Actor>> get() = _actorsLiveData
    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is SocketTimeoutException, is UnknownHostException, is HttpException -> {
                _errorLiveData.value = R.string.internet_error
            }
            else -> _errorLiveData.value = R.string.unknown_error
        }
    }

    fun getFullMovies(movieId: Int) {
        viewModelScope.launch(exceptionHandler) {
            _moviesLiveData.value = getMovieDetailsUseCase.invoke(movieId)
        }
    }

    fun getActors(movieId: Int) {
        viewModelScope.launch(exceptionHandler) {
            _actorsLiveData.value = getActorUseCase.invoke(movieId)
        }
    }
}