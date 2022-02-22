package com.example.bestapplication.data.repository

import android.content.Context
import android.widget.Toast
import com.example.bestapplication.R
import com.example.bestapplication.data.model.*
import com.example.bestapplication.data.network.MoviesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepository @Inject constructor(private val api: MoviesService) {
    private val lang = "en-US"
    private val key = "641666dd6d279ab35afbe0cdfe384006"
    private val page = 1

    suspend fun loadMovies(context: Context): List<MoviePreview>? {
        var moviePreviewList: List<MoviePreview>? = null
        try {
            moviePreviewList = withContext(Dispatchers.IO) {
                api.loadMovies(key, lang, page).results
            }
        } catch (e: IOException) {
            Toast.makeText(
                context, R.string.name_exception,
                Toast.LENGTH_SHORT
            ).show()
        }
        return moviePreviewList
    }

    suspend fun getGenres(context: Context): List<Genre>? {
        var genres: List<Genre>? = null
        try {
            genres = withContext(Dispatchers.IO) {
                api.getGenres(key, lang).genres
            }
        } catch (e: IOException) {
            Toast.makeText(
                context,
                R.string.name_exception,
                Toast.LENGTH_SHORT
            ).show()
        }
        return genres
    }

    suspend fun getMovie(context: Context, movieId: Int): MovieFull? {
        var movieFull: MovieFull? = null
        try {
            movieFull = withContext(Dispatchers.IO) {
                api.getMovie(movieId, key, lang)
            }
        } catch (e: IOException) {
            Toast.makeText(
                context,
                R.string.name_exception,
                Toast.LENGTH_SHORT
            ).show()
        }
        return movieFull
    }

    suspend fun getActors(context: Context, movieId: Int): List<Actor>? {
        var actors: List<Actor>? = null
        try {
            actors = withContext(Dispatchers.IO) {
                api.getActors(movieId, key, lang).actors
            }
        } catch (e: IOException) {
            Toast.makeText(
                context,
                R.string.name_exception,
                Toast.LENGTH_SHORT
            ).show()
        }
        return actors
    }

    suspend fun getShareLink(movieId: Int): ShareLink {
        val shareLink: ShareLink?
        shareLink = withContext(Dispatchers.IO) {
            api.getShareLink(movieId, key, lang).resultLink.link
        }
        return shareLink
    }

}