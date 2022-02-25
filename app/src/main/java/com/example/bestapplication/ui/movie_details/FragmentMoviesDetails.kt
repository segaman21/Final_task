package com.example.bestapplication.ui.movie_details

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bestapplication.R
import com.example.bestapplication.data.model.Actor
import com.example.bestapplication.data.model.Genre
import com.example.bestapplication.data.model.MovieFull
import com.example.bestapplication.databinding.FragmentMoviesDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_details.*

@AndroidEntryPoint
class FragmentMoviesDetails : Fragment() {
    private val viewModel by viewModels<MovieDetailsViewModel>()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieId = arguments?.getInt(MOVIE_ID)
        initListeners()
        initObservers(movieId)
        if (movieId != null) {
            viewModel.getActors(movieId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers(movieId: Int?) {
        var actors: List<Actor>? = null
        viewModel.moviesLiveData.observe(viewLifecycleOwner, {
            bind(it, actors)
        })
        viewModel.actorsLiveData.observe(viewLifecycleOwner, {
            actors = it
            if (movieId != null) {
                viewModel.getMovies(movieId)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun bind(movie: MovieFull, actors: List<Actor>?) {
        setRate(movie.ratings)
        val posterUrl = "https://image.tmdb.org/t/p/original/${movie.backdrop}"
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

    private fun initListeners() {
        imageView_back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun shareMovieDetails(sharedLink: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, sharedLink)
        startActivity(intent)
    }

    companion object {
        private const val MOVIE_ID = "movie"
        fun newInstance(moviePreview: Int): FragmentMoviesDetails {
            val fragment = FragmentMoviesDetails()
            val args = Bundle()
            args.putInt(MOVIE_ID, moviePreview)
            fragment.arguments = args
            return fragment
        }
    }
}
