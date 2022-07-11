package com.example.bestapplication.ui.movie_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bestapplication.R
import com.example.bestapplication.databinding.FragmentMoviesListBinding
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.utilites.Keys.ID
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movies_list.*
import javax.inject.Inject

class FragmentMoviesList : DaggerFragment() {
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var genreList = listOf<Genre>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MovieListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        viewModel.getGenres()
        viewModel.getFilms()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        val movieAdapter = MovieListAdapter(onClick = {
            val bundle = bundleOf(ID to it.id)
            findNavController().navigate(
                R.id.action_viewPagerFragment_to_fragmentMoviesDetails,
                bundle
            )
        })
        viewModel.genresLiveData.observe(viewLifecycleOwner) {
            genreList = it
            movieAdapter.setGenres(genreList)
        }
        movies_list.adapter = movieAdapter
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            movies_list.layoutManager = GridLayoutManager(context, 2)
        } else {
            movies_list.layoutManager = GridLayoutManager(context, 4)
        }
        viewModel.filmsLiveData.observe(viewLifecycleOwner) { movies ->
            movieAdapter.submitList(movies)
        }
    }
}
