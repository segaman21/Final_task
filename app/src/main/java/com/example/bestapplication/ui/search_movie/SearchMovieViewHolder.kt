package com.example.bestapplication.ui.search_movie

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestapplication.R
import com.example.bestapplication.databinding.SearchMovieItemBinding
import com.example.bestapplication.favorite_movie.entity.FavoriteMovie
import com.example.bestapplication.utilites.Keys


class SearchMovieViewHolder(private val binding: SearchMovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: FavoriteMovie, listener: (FavoriteMovie) -> Unit) {
        setName(movie)
        setThumbnail(movie)
        setClickListener(listener, movie)
    }

    private fun setClickListener(
        listener: (FavoriteMovie) -> Unit,
        movie: FavoriteMovie
    ) {
        itemView.setOnClickListener { listener(movie) }
    }

    private fun setName(movie: FavoriteMovie) {
        binding.favoriteMovieTitle.text = movie.title
    }

    private fun setThumbnail(movie: FavoriteMovie) {
        val imageUrl = Keys.POSTER_URL + movie.poster
        Glide.with(itemView)
            .load(imageUrl)
            .placeholder(R.drawable.ic_download)
            .centerCrop()
            .into(binding.favoriteMoviePoster)
    }
}
