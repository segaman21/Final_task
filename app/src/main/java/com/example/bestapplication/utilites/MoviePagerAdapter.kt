package com.example.bestapplication.utilites

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bestapplication.ui.search_movie.SearchFragment
import com.example.bestapplication.ui.favorite_movie.FragmentFavoriteMovies
import com.example.bestapplication.ui.movie_list.FragmentMoviesList

const val MOVIE_LIST_PAGE_INDEX = 0
const val MOVIE_FAVORITE_PAGE_INDEX = 1
const val MOVIE_SEARCH_PAGE_INDEX = 2

class MoviePagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MOVIE_LIST_PAGE_INDEX to { FragmentMoviesList() },
        MOVIE_FAVORITE_PAGE_INDEX to { FragmentFavoriteMovies() },
        MOVIE_SEARCH_PAGE_INDEX to { SearchFragment() }

    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}