package com.example.bestapplication.moviedetails

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.fragment_movies_details.*


class FragmentMoviesDetails : Fragment() {
    private val viewModel by viewModels<MovieDetailsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieId = arguments?.getInt(MOVIE_ID)
        initListeners()
        initObservers(movieId)
        viewModel.getActors(requireActivity(), movieId!!)
    }

    private fun initObservers(movieId: Int?) {
        var actors: List<Actor>? = null
        viewModel.moviesLiveData.observe(viewLifecycleOwner, {
            bind(it, actors)
        })
        viewModel.actorsLiveData.observe(viewLifecycleOwner, {
            actors = it
            if (movieId != null) {
                viewModel.getMovies(requireActivity(), movieId)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun bind(movie: MovieFull?, actors: List<Actor>?) {
        setRate(movie!!.ratings)
        val posterUrl = "https://image.tmdb.org/t/p/original/${movie.backdrop}"
        Glide.with(requireActivity())
            .load(posterUrl)
            .placeholder(R.drawable.arrow)
            .centerCrop()
            .into(imageView_title)
        textView_age.text = if (movie.minimumAge) R.string.minimum_age_16.toString() else  R.string.minimum_age_13.toString()
        tv_title_movie.text = movie.title
        reviews_film?.text = "${movie.numberOfRatings} reviews"
        textView_story.text = movie.overview
        textView_genre.text = setGenres(movie.genres)

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
            setRedStar(imageView_star_one)
        } else {
            setGrayStar(imageView_star_one)
        }
        if (rate >= 4F) {
            setRedStar(imageView_star_two)
        } else {
            setGrayStar(imageView_star_two)
        }
        if (rate >= 6F) {
            setRedStar(imageView_star_three)
        } else {
            setGrayStar(imageView_star_three)
        }
        if (rate >= 8F) {
            setRedStar(imageView_star_four)
        } else {
            setGrayStar(imageView_star_four)
        }
        if (rate == 10F) {
            setRedStar(imageView_star_five)
        } else {
            setGrayStar(imageView_star_five)
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
