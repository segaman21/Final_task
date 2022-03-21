package com.example.bestapplication.presentation.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Genre
import com.example.bestapplication.GetMovieListUseCase
import com.example.bestapplication.models.MoviePreview
import com.example.data.MoviesRepositoryImp

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repositoryImp: MoviesRepositoryImp,
    private val getMovieList: GetMovieListUseCase
) : ViewModel() {

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
            _genresLiveData.value = repositoryImp.getGenres()
        }
    }
}