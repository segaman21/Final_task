package com.example.bestapplication.ui.favorite_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bestapplication.data.database.MovieEntity
import com.example.bestapplication.databinding.FragmentItemFavoriteMovieBinding
import kotlinx.serialization.ExperimentalSerializationApi

typealias OnMovieClick = (MovieEntity) -> Unit

class FavoriteMoviesAdapter(
    private val onClick: OnMovieClick
) : ListAdapter<MovieEntity, FavoriteMovieViewHolder>(MovieDiffCallback) {

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

object MovieDiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(
        oldItem: MovieEntity,
        newItem: MovieEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieEntity,
        newItem: MovieEntity
    ): Boolean {
        return oldItem == newItem
    }
}