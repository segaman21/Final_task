package com.example.bestapplication.ui.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestapplication.data.model.Genre
import com.example.bestapplication.data.model.MoviePreview
import com.example.bestapplication.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MoviesRepository) :
    ViewModel() {
    private val _filmsLiveData = MutableLiveData<List<MoviePreview>>()
    val filmsLiveData: LiveData<List<MoviePreview>> get() = _filmsLiveData
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genresLiveData: LiveData<List<Genre>> get() = _genresLiveData

    fun getFilms() {
        viewModelScope.launch {
            _filmsLiveData.value = repository.loadMovies()
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            _genresLiveData.value = repository.getGenres()
        }
    }
}