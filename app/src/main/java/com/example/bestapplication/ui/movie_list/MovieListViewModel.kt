package com.example.bestapplication.ui.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestapplication.R
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.movie_list.entity.MoviePreview
import com.example.bestapplication.movie_list.usecase.GetGenreUseCase
import com.example.bestapplication.movie_list.usecase.GetMovieListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getGenres: GetGenreUseCase,
    private val getMovieList: GetMovieListUseCase
) : ViewModel() {
    private val _filmsLiveData = MutableLiveData<List<MoviePreview>>()
    val filmsLiveData: LiveData<List<MoviePreview>> get() = _filmsLiveData
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genresLiveData: LiveData<List<Genre>> get() = _genresLiveData
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

    fun getFilms() {
        viewModelScope.launch(exceptionHandler) {
            _filmsLiveData.value = getMovieList.invoke()
        }

    }

    fun getGenres() {
        viewModelScope.launch(exceptionHandler) {
            _genresLiveData.value = getGenres.invoke()
        }
    }
}