package com.example.bestapplication.di

import com.example.bestapplication.GetMovieListUseCase
import com.example.bestapplication.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetMovieListUseCase(repository: MovieRepository): GetMovieListUseCase {
        return GetMovieListUseCase(repository)
    }
}