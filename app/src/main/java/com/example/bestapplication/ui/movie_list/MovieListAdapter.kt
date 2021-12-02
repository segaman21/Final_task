package com.example.bestapplication.ui.movie_list

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.bestapplication.data.model.Genre
import com.example.bestapplication.data.model.MoviePreview
import com.example.bestapplication.databinding.FragmentMovieBinding

class MovieListAdapter(private val moviesList: List<MoviePreview>, val genres: List<Genre>) :
    RecyclerView.Adapter<MovieListViewHolder>() {
    lateinit var callback: Callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view =FragmentMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.onBind(moviesList[position], genres, this.callback)
    }

    fun initCallback(callback: FragmentMoviesList) {
        this.callback = callback
    }

    interface Callback {
        fun startMovieDetailsFragment(item: MoviePreview)
    }

}
