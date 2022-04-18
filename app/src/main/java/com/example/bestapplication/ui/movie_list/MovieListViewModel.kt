package com.example.bestapplication.ui.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.movie_list.entity.MoviePreview
import com.example.bestapplication.movie_list.usecase.GetGenreUseCase
import com.example.bestapplication.movie_list.usecase.GetMovieListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getGenres: GetGenreUseCase,
    private val getMovieList: GetMovieListUseCase
) :
    ViewModel() {
    private val _filmsLiveData = MutableLiveData<List<MoviePreview>>()
    val filmsLiveData: LiveData<List<MoviePreview>> get() = _filmsLiveData
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genresLiveData: LiveData<List<Genre>> get() = _genresLiveData

    fun getFilms() {
        viewModelScope.launch {
            _filmsLiveData.value = getMovieList.invoke()
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            _genresLiveData.value = getGenres.invoke()
        }
    }
}