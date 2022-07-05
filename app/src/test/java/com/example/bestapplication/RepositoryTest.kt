package com.example.bestapplication

import com.example.bestapplication.movie_details.entity.Actor
import com.example.bestapplication.movie_details.usecase.GetActorUseCase
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.movie_list.entity.MoviePreview
import com.example.bestapplication.movie_list.usecase.GetGenreUseCase
import com.example.bestapplication.movie_list.usecase.GetMovieListUseCase
import com.example.bestapplication.repository.MovieRepository
import com.example.bestapplication.utils.UnitTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class RepositoryTest : UnitTest() {

    private lateinit var getGenreUseCase: GetGenreUseCase

    private lateinit var getActorUseCase: GetActorUseCase

    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @Mock
    lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `get movie list use case`() {
        getMovieListUseCase = GetMovieListUseCase(movieRepository)
        runBlocking {
            Mockito.`when`(movieRepository.loadMovies()).thenReturn(listOf<MoviePreview>())
            val response = getMovieListUseCase.invoke()
            assertEquals(listOf<MoviePreview>(), response)
        }
    }

    @Test
    fun `get genres use case`() {
        getGenreUseCase = GetGenreUseCase(movieRepository)
        runBlocking {
            Mockito.`when`(movieRepository.loadGenres()).thenReturn(listOf<Genre>())
            val response = getGenreUseCase.invoke()
            assertEquals(listOf<MoviePreview>(), response)
        }
    }

    @Test
    fun `get actors use case`() {
        getActorUseCase = GetActorUseCase(movieRepository)
        runBlocking {
            Mockito.`when`(movieRepository.loadActors(1)).thenReturn(listOf<Actor>())
            val response = getActorUseCase.invoke(1)
            assertEquals(listOf<MoviePreview>(), response)
        }
    }

}