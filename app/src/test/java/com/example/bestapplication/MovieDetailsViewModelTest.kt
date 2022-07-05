package com.example.bestapplication

import com.example.bestapplication.movie_details.entity.Actor
import com.example.bestapplication.movie_details.entity.MovieFull
import com.example.bestapplication.movie_details.usecase.GetActorUseCase
import com.example.bestapplication.movie_details.usecase.GetMovieDetailsUseCase
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.ui.movie_details.MovieDetailsViewModel
import com.example.bestapplication.utils.AndroidTest
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class MovieDetailsViewModelTest : AndroidTest() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @MockK
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @MockK
    private lateinit var getActorUseCase: GetActorUseCase

    private val genreList = listOf(
        Genre(1, ""), Genre(2, "")
    )

    private val moviesFull = MovieFull(
        1,
        "ironman",
        "",
        "",
        2.1F,
        1,
        genreList,
        true,
        21,
        ""
    )

    private val actorList = listOf(
        Actor(
            1,
            "maks",
            ""
        ),
        Actor(
            2,
            "robert",
            ""
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieDetailsViewModel = MovieDetailsViewModel(getActorUseCase, getMovieDetailsUseCase)
    }

    @Test
    fun `movies actor should update live data`() {

        coEvery { getActorUseCase.invoke(MOVIE_ID) } returns (
                listOf(
                    Actor(
                        1,
                        "maks",
                        ""
                    ),
                    Actor(
                        2,
                        "robert",
                        ""
                    )
                ))

        runBlocking {
            movieDetailsViewModel.getActors(MOVIE_ID)
            val result = movieDetailsViewModel.actorsLiveData.value
            Assert.assertEquals(actorList, result)
        }
    }

    @Test
    fun `movies details should update live data`() {

        coEvery { getMovieDetailsUseCase.invoke(MOVIE_ID) } returns (
                MovieFull(
                    1,
                    "ironman",
                    "",
                    "",
                    2.1F,
                    1,
                    genreList,
                    true,
                    21,
                    ""
                )
                )

        runBlocking {
            movieDetailsViewModel.getFullMovies(MOVIE_ID)
            val result = movieDetailsViewModel.moviesLiveData.value
            Assert.assertEquals(moviesFull, result)
        }
    }

    companion object {
        private const val MOVIE_ID = 1
    }
}