package com.example.bestapplication

import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.movie_list.entity.MoviePreview
import com.example.bestapplication.movie_list.usecase.GetGenreUseCase
import com.example.bestapplication.movie_list.usecase.GetMovieListUseCase
import com.example.bestapplication.ui.movie_list.MovieListViewModel
import com.example.bestapplication.utils.AndroidTest
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class MovieListViewModelTest : AndroidTest() {

    private lateinit var movieListViewModel: MovieListViewModel

    @MockK
    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @MockK
    private lateinit var getGenreUseCase: GetGenreUseCase

    private val moviesList = listOf(
        MoviePreview(
            1,
            "ironman",
            "123",
            2.1F,
            1,
            listOf(1, 2),
            true
        ), MoviePreview(
            2,
            "avengers",
            "123",
            2.2F,
            3,
            listOf(1, 2),
            false
        )
    )

    private val genresList = listOf(
        Genre(
            1,
            "action"
        ), Genre(
            2,
            "comedy"
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieListViewModel = MovieListViewModel(getGenreUseCase, getMovieListUseCase)
    }

    @Test
    fun `movie list should update live data`() {
        coEvery { getMovieListUseCase.invoke() } returns (
                listOf<MoviePreview>(
                    MoviePreview(
                        1,
                        "ironman",
                        "123",
                        2.1F,
                        1,
                        listOf(1, 2),
                        true
                    ), MoviePreview(
                        2,
                        "avengers",
                        "123",
                        2.2F,
                        3,
                        listOf(1, 2),
                        false
                    )
                )
                )

        runBlocking {
            movieListViewModel.getFilms()
            val result = movieListViewModel.filmsLiveData.value
            assertEquals(moviesList, result)
        }
    }

    @Test
    fun `empty movie list test`() {
        coEvery { getMovieListUseCase.invoke() } returns (
                listOf<MoviePreview>())
        runBlocking {
            movieListViewModel.getFilms()
            val result = movieListViewModel.filmsLiveData.value
            assertEquals(listOf<MoviePreview>(), result)
        }
    }

    @Test
    fun `genre list should update live data`() {
        coEvery { getGenreUseCase.invoke() } returns (
                listOf(
                    Genre(
                        1,
                        "action"
                    ), Genre(
                        2,
                        "comedy"
                    )
                ))

        runBlocking {
            movieListViewModel.getGenres()
            val result = movieListViewModel.genresLiveData.value
            assertEquals(genresList, result)
        }
    }

    @Test
    fun `empty genre list test`() {
        coEvery { getGenreUseCase.invoke() } returns (
                listOf<Genre>())
        runBlocking {
            movieListViewModel.getGenres()
            val result = movieListViewModel.genresLiveData.value
            assertEquals(listOf<Genre>(), result)
        }
    }
}
