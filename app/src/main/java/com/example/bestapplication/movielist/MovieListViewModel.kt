package com.example.bestapplication.movielist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestapplication.data.model.Genre
import com.example.bestapplication.data.model.MoviePreview
import com.example.bestapplication.data.network.MoviesRepository
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val _filmsLiveData = MutableLiveData<List<MoviePreview>>()
    val filmsLiveData: LiveData<List<MoviePreview>> get() = _filmsLiveData
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genresLiveData: LiveData<List<Genre>> get() = _genresLiveData
    private val repository = MoviesRepository()

    fun getFilms(context: Context) {
        viewModelScope.launch {
            _filmsLiveData.value = repository.loadMovies(context)
        }
    }
    fun getGenres(context: Context){
        viewModelScope.launch {
            _genresLiveData.value=repository.getGenres(context)
        }
    }
}