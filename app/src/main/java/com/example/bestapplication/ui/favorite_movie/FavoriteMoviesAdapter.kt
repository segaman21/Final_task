package com.example.bestapplication.ui.favorite_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bestapplication.databinding.FragmentItemFavoriteMovieBinding
import com.example.bestapplication.favorite_movie.entity.MovieDatabaseEntity
import kotlinx.serialization.ExperimentalSerializationApi

typealias OnMovieClick = (MovieDatabaseEntity) -> Unit

class FavoriteMoviesAdapter(private val onClick: OnMovieClick) :
    ListAdapter<MovieDatabaseEntity, FavoriteMovieViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMovieViewHolder {
        val itemBinding =
            FragmentItemFavoriteMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FavoriteMovieViewHolder(itemBinding, onClick)
    }

    @ExperimentalSerializationApi
    override fun onBindViewHolder(
        holder: FavoriteMovieViewHolder,
        position: Int
    ) {
        val movie = getItem(position)
        holder.bind(movie)
    }
}

object MovieDiffCallback : DiffUtil.ItemCallback<MovieDatabaseEntity>() {
    override fun areItemsTheSame(
        oldItem: MovieDatabaseEntity,
        newItem: MovieDatabaseEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieDatabaseEntity,
        newItem: MovieDatabaseEntity
    ): Boolean {
        return oldItem == newItem
    }
}