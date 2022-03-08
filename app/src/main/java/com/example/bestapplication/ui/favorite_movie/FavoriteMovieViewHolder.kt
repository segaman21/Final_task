package com.example.bestapplication.ui.favorite_movie

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestapplication.R
import com.example.bestapplication.data.database.MovieEntity
import com.example.bestapplication.data.model.Genre
import com.example.bestapplication.databinding.FragmentItemFavoriteMovieBinding
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FavoriteMovieViewHolder(
    private val itemBinding: FragmentItemFavoriteMovieBinding,
    private val onClick: OnMovieClick
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private var currentMovie: MovieEntity? = null

    init {
        itemBinding.favoriteMovieCard.setOnClickListener {
            currentMovie?.let { onClick(it) }
        }
    }


    @ExperimentalSerializationApi
    fun bind(movie: MovieEntity) {
        currentMovie=movie
        loadPoster(movie.poster, itemBinding.root.context)
        val genres = Json.decodeFromString<List<Genre>>(movie.genres)
        itemBinding.apply {
            favoriteMovieGenres.text = setGenres(genres)
            favoriteMovieRuntime.text = "${movie.runtime} min"
            itemBinding.favoriteMovieTitle.text = movie.title
            favoriteMovieRatings.text = movie.ratings.toString()
        }
    }

    private fun setGenres(genres: List<Genre>): String {
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

    private fun loadPoster(poster: String, context: Context) {
        val posterUrl = "https://image.tmdb.org/t/p/original/${poster}"
        Glide.with(context)
            .load(posterUrl)
            .placeholder(R.drawable.ic_download)
            .centerCrop()
            .into(itemBinding.favoriteMoviePoster)
    }
}