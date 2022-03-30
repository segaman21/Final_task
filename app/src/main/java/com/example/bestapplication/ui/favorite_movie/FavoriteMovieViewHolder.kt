package com.example.bestapplication.ui.favorite_movie

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestapplication.R
import com.example.bestapplication.model.GenreApi
import com.example.bestapplication.databinding.FragmentItemFavoriteMovieBinding
import com.example.bestapplication.favorite_movie.entity.MovieDatabaseEntity
import com.example.bestapplication.utilites.Keys.POSTER_URL
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@SuppressLint("ClickableViewAccessibility")
class FavoriteMovieViewHolder(
    private val itemBinding: FragmentItemFavoriteMovieBinding,
    private val onClick: OnMovieClick
) : RecyclerView.ViewHolder(itemBinding.root) {

    private var currentMovie: MovieDatabaseEntity? = null

    init {
        itemBinding.favoriteMovieCard.setOnClickListener {
            currentMovie?.let { onClick(it) }
        }
    }

    @SuppressLint("SetTextI18n")
    @ExperimentalSerializationApi
    fun bind(movie: MovieDatabaseEntity) {
        currentMovie = movie
        loadPoster(movie.poster, itemBinding.root.context)
        val genres = Json.decodeFromString<List<GenreApi>>(movie.genres)
        itemBinding.apply {
            favoriteMovieGenres.text = setGenres(genres)
            favoriteMovieRuntime.text = "${movie.runtime} min"
            itemBinding.favoriteMovieTitle.text = movie.title
            favoriteMovieRatings.text = movie.ratings.toString()
        }
    }

    private fun setGenres(genres: List<GenreApi>): String {
        var genresStr = ""
        for (i in genres.indices) {
            genresStr += if (i == genres.size - 1) {
                genres[i].name
            } else {
                "${genres[i].name}, "
            }
        }
        return genresStr
    }

    private fun loadPoster(
        poster: String,
        context: Context
    ) {
        val posterUrl = POSTER_URL + poster
        Glide.with(context)
            .load(posterUrl)
            .placeholder(R.drawable.ic_download)
            .centerCrop()
            .into(itemBinding.favoriteMoviePoster)
    }
}