package com.example.bestapplication.di.modules

import com.example.bestapplication.ui.ViewPagerFragment
import com.example.bestapplication.ui.favorite_movie.FragmentFavoriteMovies
import com.example.bestapplication.ui.movie_details.FragmentMoviesDetails
import com.example.bestapplication.ui.movie_list.FragmentMoviesList
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentMoviesList(): FragmentMoviesList

    @ContributesAndroidInjector
    abstract fun contributeFragmentMoviesDetails(): FragmentMoviesDetails

    @ContributesAndroidInjector
    abstract fun contributeFragmentFavoriteMovies(): FragmentFavoriteMovies

    @ContributesAndroidInjector
    abstract fun contributeViewPagerFragment(): ViewPagerFragment
}