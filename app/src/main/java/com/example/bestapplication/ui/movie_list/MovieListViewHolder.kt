package com.example.bestapplication.ui.movie_list

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestapplication.R
import com.example.bestapplication.data.model.Genre
import com.example.bestapplication.data.model.MoviePreview
import com.example.bestapplication.databinding.FragmentMovieBinding

class MovieListViewHolder(private val binding: FragmentMovieBinding, onClick: OnMovieClick) :
    RecyclerView.ViewHolder(binding.root) {
    private var currentFilm: MoviePreview? = null

    init {
        binding.filmCard.setOnClickListener {
            currentFilm?.let {
                onClick(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun onBind(item: MoviePreview, genreList: List<Genre>) {
        currentFilm = item
        val genres = mutableListOf<Genre>()
        for (element in item.genres) {
            val genre = genreList.firstOrNull { it.id == element }
            if (genre != null) {
                genres.add(genre)
            }
        }
        setRate(item.ratings)
        binding.genreDescription.text = setGenres(genres)
        binding.movieName.text = item.title
        binding.howReviews.text = "${item.numberOfRatings} reviews"
        binding.tvAge.text = if (item.minimumAge) "+16" else "+13"
        val posterUrl = "https://image.tmdb.org/t/p/original/${item.poster}"
        Glide.with(itemView)
            .load(posterUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_download)
            .into(binding.imageViewMask)
    }

    private fun setRate(rate: Float) {
        if (rate >= 2.0F) {
            setRedStar(binding.firstStar)
        } else {
            setGrayStar(binding.firstStar)
        }

        if (rate >= 4.0F) {
            setRedStar(binding.secondStar)
        } else {
            setGrayStar(binding.secondStar)
        }

        if (rate >= 6.0F) {
            setRedStar(binding.thirdStar)
        } else {
            setGrayStar(binding.thirdStar)
        }

        if (rate >= 8.0F) {
            setRedStar(binding.fourStar)
        } else {
            setGrayStar(binding.fourStar)
        }

        if (rate == 10.0F) {
            setRedStar(binding.fiveStar)
        } else {
            setGrayStar(binding.fiveStar)
        }

        if (rate > 10)
            throw IllegalStateException("Illegal rating value")
    }

    private fun setGrayStar(starView: ImageView) {
        starView.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                R.drawable.small_gray
            )
        )
    }

    private fun setRedStar(starView: ImageView) {
        starView.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                R.drawable.small_red
            )
        )
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
}