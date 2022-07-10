package com.example.bestapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bestapplication.R
import com.example.bestapplication.databinding.FragmentViewPagerBinding
import com.example.bestapplication.ui.favorite_movie.FavoriteMovieViewModel
import com.example.bestapplication.utilites.*
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_view_pager.*
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewPagerFragment : DaggerFragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private var onSwitch = SwitchThemeListener()
    private lateinit var moviesAdapter: MoviesAdapter

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
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager
        viewPager.adapter = MoviePagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        binding.moviesList.apply {
            layoutManager = GridLayoutManager(context, 1)
            moviesAdapter = MoviesAdapter {
            }
            adapter = moviesAdapter
        }
        return binding.root
    }

    @OptIn(ObsoleteCoroutinesApi::class)
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        switch_theme.setOnCheckedChangeListener { _, isChecked ->
            onSwitch.switch(isChecked)
        }

        if (savedInstanceState == null) {
            lifecycleScope.launch {
                favoriteMovieViewModel.queryChannel.send("")
            }
        }
        binding.searchMovie.afterTextChanged {
            lifecycleScope.launch {
                favoriteMovieViewModel.queryChannel.send(it)
            }
        }

        favoriteMovieViewModel.searchResult.observe(viewLifecycleOwner) {
            moviesAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MOVIE_LIST_PAGE_INDEX -> getString(R.string.movie_list)
            MOVIE_FAVORITE_PAGE_INDEX -> getString(R.string.favorite_movies)
            else -> null
        }
    }
}