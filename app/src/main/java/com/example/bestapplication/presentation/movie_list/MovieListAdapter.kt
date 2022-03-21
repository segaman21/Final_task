package com.example.bestapplication.presentation.movie_list

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.data.model.Genre
import com.example.bestapplication.databinding.FragmentMovieBinding
import com.example.bestapplication.models.MoviePreview

typealias OnMovieClick = (MoviePreview) -> Unit

class MovieListAdapter(
    private val onClick: OnMovieClick
) : ListAdapter<MoviePreview, MovieListViewHolder>(MovieDiffUtils) {
    private var genres: List<Genre>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListViewHolder {
        val view = FragmentMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(view, onClick)
    }

    override fun onBindViewHolder(
        holder: MovieListViewHolder,
        position: Int
    ) {
        val movie = getItem(position)
        genres?.let { holder.onBind(movie, it) }

    }

    object MovieDiffUtils : DiffUtil.ItemCallback<MoviePreview>() {
        override fun areItemsTheSame(
            oldItem: MoviePreview,
            newItem: MoviePreview
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MoviePreview,
            newItem: MoviePreview
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setGenres(genre: List<Genre>) {
        genres = genre
    }
}

