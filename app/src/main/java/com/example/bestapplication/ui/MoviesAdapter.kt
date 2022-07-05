package com.example.bestapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bestapplication.databinding.SearchMovieItemBinding
import com.example.bestapplication.favorite_movie.entity.FavoriteMovie

class MoviesAdapter(private val listener: (FavoriteMovie) -> Unit) :
    ListAdapter<FavoriteMovie, MovieViewHolder>(DIFF_CALLBACK) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = SearchMovieItemBinding.inflate(layoutInflater, parent, false)
    return MovieViewHolder(binding)
  }

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    holder.bind(getItem(position), listener)
  }

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteMovie>() {
      override fun areItemsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean =
          oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean =
          oldItem == newItem
    }
  }
}