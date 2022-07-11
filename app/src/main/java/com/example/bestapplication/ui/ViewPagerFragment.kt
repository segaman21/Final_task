package com.example.bestapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

class ViewPagerFragment : Fragment() {
    private var onSwitch = SwitchThemeListener()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerBinding.inflate(
            inflater,
            container,
            false
        )
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = MoviePagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        switch_theme.setOnCheckedChangeListener { _, isChecked ->
            onSwitch.switch(isChecked)
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MOVIE_LIST_PAGE_INDEX -> getString(R.string.movie_list)
            MOVIE_FAVORITE_PAGE_INDEX -> getString(R.string.favorite_movies)
            MOVIE_SEARCH_PAGE_INDEX -> getString(R.string.search_movies)
            else -> null
        }
    }
}