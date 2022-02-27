package com.example.bestapplication.ui.movie_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bestapplication.R
import com.example.bestapplication.SwitchTheme
import com.example.bestapplication.data.model.Genre
import com.example.bestapplication.databinding.FragmentMoviesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_list.*

@AndroidEntryPoint
class FragmentMoviesList : Fragment() {
    private var switchTheme = SwitchTheme()
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var genreList = listOf<Genre>()
    private val viewModel by viewModels<MovieListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getGenres()
        viewModel.getFilms()
        initObservers()
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            switchTheme.switch(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        val movieAdapter = MovieListAdapter(onClick = {
            val bundle = bundleOf("ID" to it.id)
            findNavController().navigate(
                R.id.action_fragmentMoviesList_to_fragmentMoviesDetails,
                bundle
            )
        })
        viewModel.genresLiveData.observe(viewLifecycleOwner, {
            genreList = it
            movieAdapter.setGenres(genreList)
        })
        movies_list.adapter = movieAdapter
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            movies_list.layoutManager = GridLayoutManager(context, 2)
        } else {
            movies_list.layoutManager = GridLayoutManager(context, 4)
        }
        viewModel.filmsLiveData.observe(viewLifecycleOwner, { movies ->
            movieAdapter.submitList(movies)
        })
    }
}
