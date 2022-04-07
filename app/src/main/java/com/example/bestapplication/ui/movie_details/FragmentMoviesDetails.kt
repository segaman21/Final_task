package com.example.bestapplication.ui.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bestapplication.R
import com.example.bestapplication.ui.favorite_movie.FavoriteMovieViewModel
import com.example.bestapplication.databinding.FragmentMoviesDetailsBinding
import com.example.bestapplication.movie_details.entity.Actor
import com.example.bestapplication.movie_details.entity.MovieFull
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.utilites.Keys.ID
import com.example.bestapplication.utilites.Keys.POSTER_URL
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_details.*
import kotlinx.serialization.ExperimentalSerializationApi

@AndroidEntryPoint
class FragmentMoviesDetails : Fragment() {
    private val detailsMovieViewModel by viewModels<MovieDetailsViewModel>()
    private val favoriteMovieViewModel by viewModels<FavoriteMovieViewModel>()
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalSerializationApi
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        val movieId = arguments?.getInt(ID)
        initObservers(movieId)
        binding.fab?.let { initMovieInDatabaseListener(it) }
        if (movieId != null) {
            favoriteMovieViewModel.checkMovieInDatabase(movieId)
            binding.fab?.setOnClickListener {
                hideAppBar(fab)
                favoriteMovieViewModel.insertMovieToDatabase(movieId)
            }
            detailsMovieViewModel.getActors(movieId)
        }
        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers(movieId: Int?) {
        var actors: List<Actor>? = null
        detailsMovieViewModel.moviesLiveData.observe(viewLifecycleOwner) {
            bind(it, actors)
        }
        detailsMovieViewModel.actorsLiveData.observe(viewLifecycleOwner) {
            actors = it
            if (movieId != null) {
                detailsMovieViewModel.getFullMovies(movieId)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bind(
        movie: MovieFull,
        actors: List<Actor>?
    ) {
        setRate(movie.ratings)
        val posterUrl = POSTER_URL + movie.backdrop
        Glide.with(requireActivity())
            .load(posterUrl)
            .placeholder(R.drawable.ic_download)
            .centerCrop()
            .into(binding.imageViewTitle)
        binding.textViewAge.text = if (movie.minimumAge) "+16" else "+13"
        binding.tvTitleMovie.text = movie.title
        binding.reviewsFilm.text = "${movie.numberOfRatings} reviews"
        binding.textViewStory.text = movie.overview
        binding.textViewGenre.text = setGenres(movie.genres)
        val movieDetailsAdapter = actors?.let { ActorAdapter(it) }
        recycler_name.adapter = movieDetailsAdapter
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler_name.layoutManager = linearLayoutManager
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

    private fun setRate(rate: Float) {
        if (rate >= 2F) {
            setRedStar(binding.imageViewStarOne)
        } else {
            setGrayStar(binding.imageViewStarOne)
        }
        if (rate >= 4F) {
            setRedStar(binding.imageViewStarTwo)
        } else {
            setGrayStar(binding.imageViewStarTwo)
        }
        if (rate >= 6F) {
            setRedStar(binding.imageViewStarThree)
        } else {
            setGrayStar(binding.imageViewStarThree)
        }
        if (rate >= 8F) {
            setRedStar(binding.imageViewStarFour)
        } else {
            setGrayStar(binding.imageViewStarFour)
        }
        if (rate == 10F) {
            setRedStar(binding.imageViewStarFive)
        } else {
            setGrayStar(binding.imageViewStarFive)
        }
    }

    private fun setGrayStar(starView: ImageView) {
        starView.setImageDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.star_gray
            )
        )
    }

    private fun setRedStar(starView: ImageView) {
        starView.setImageDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.star_red
            )
        )
    }

    private fun initMovieInDatabaseListener(fab: FloatingActionButton) {
        favoriteMovieViewModel.isFavoriteLiveData.observe(viewLifecycleOwner) {
            if (it) {
                val params = fab.layoutParams as CoordinatorLayout.LayoutParams
                val behavior = params.behavior as? FloatingActionButton.Behavior
                behavior?.isAutoHideEnabled = false
                fab.hide()
            }
        }
    }

    private fun hideAppBar(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
        Toast.makeText(context, R.string.add_to_favorite, Toast.LENGTH_SHORT).show()
    }
}