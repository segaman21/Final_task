package com.example.bestapplication.ui.search_movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bestapplication.*
import com.example.bestapplication.databinding.FragmentSearchBinding
import com.example.bestapplication.ui.favorite_movie.FavoriteMovieViewModel
import com.example.bestapplication.utilites.*
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchMoviesAdapter: SearchMoviesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchList.apply {
            val spanCount =
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> 4
                    else -> 2
                }
            layoutManager = GridLayoutManager(activity, spanCount)
            searchMoviesAdapter = SearchMoviesAdapter() {
                val bundle = bundleOf(Keys.ID to it.id)
                findNavController().navigate(
                    R.id.action_viewPagerFragment_to_fragmentMoviesDetails,
                    bundle
                )
            }
            adapter = searchMoviesAdapter
        }
        return binding.root
    }

    @OptIn(ObsoleteCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            lifecycleScope.launch {
                favoriteMovieViewModel.queryChannel.send("")
            }
        }
        binding.searchInput.afterTextChanged {
            lifecycleScope.launch {
                favoriteMovieViewModel.queryChannel.send(it)
            }
        }
        favoriteMovieViewModel.searchResult.observe(viewLifecycleOwner) {
            handlemoviesList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handlemoviesList(it: MoviesResult) {
        when (it) {
            is ValidResult -> {
                binding.moviesPlaceholder.visibility = View.GONE
                binding.searchList.visibility = View.VISIBLE
                searchMoviesAdapter.submitList(it.result)
            }
            is ErrorResult -> {
                searchMoviesAdapter.submitList(emptyList())
                binding.moviesPlaceholder.visibility = View.VISIBLE
                binding.searchList.visibility = View.GONE
                binding.moviesPlaceholder.setText(R.string.search_error)
            }
            is EmptyResult -> {
                searchMoviesAdapter.submitList(emptyList())
                binding.moviesPlaceholder.visibility = View.VISIBLE
                binding.searchList.visibility = View.GONE
                binding.moviesPlaceholder.setText(R.string.empty_result)
            }
            is EmptyQuery -> {
                searchMoviesAdapter.submitList(emptyList())
                binding.moviesPlaceholder.visibility = View.VISIBLE
                binding.searchList.visibility = View.GONE
                binding.moviesPlaceholder.setText(R.string.movies_placeholder)
            }
        }
    }
}