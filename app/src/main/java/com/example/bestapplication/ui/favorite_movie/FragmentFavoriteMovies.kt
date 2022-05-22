package com.example.bestapplication.ui.favorite_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.bestapplication.R
import com.example.bestapplication.utilites.SwipeHelperCallback
import com.example.bestapplication.databinding.FragmentFavoriteMoviesBinding
import com.example.bestapplication.utilites.Keys.ID
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FragmentFavoriteMovies : DaggerFragment() {
    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding!!
    private var mItemTouchHelper: ItemTouchHelper? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FavoriteMovieViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        val favoriteMoviesAdapter = FavoriteMoviesAdapter(
            onClick = {
                val bundle = bundleOf(ID to it.id)
                findNavController().navigate(
                    R.id.action_viewPagerFragment_to_fragmentMoviesDetails,
                    bundle
                )
            }
        )
        val callback: ItemTouchHelper.Callback = SwipeHelperCallback {
            val movieItem = favoriteMoviesAdapter.currentList[it]
            viewModel.deleteMovieFromDatabase(movieItem)
        }
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper?.attachToRecyclerView(binding.favoriteMovieList)
        binding.favoriteMovieList.adapter = favoriteMoviesAdapter
        viewModel.favoriteMovieLiveData.observe(viewLifecycleOwner) { movie ->
            favoriteMoviesAdapter.submitList(movie)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}