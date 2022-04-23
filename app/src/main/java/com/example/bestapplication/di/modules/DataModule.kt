package com.example.bestapplication.di.modules

import com.example.bestapplication.database.MovieDao
import com.example.bestapplication.network.NetworkApi
import com.example.bestapplication.repository.FavoriteMovieRepository
import com.example.bestapplication.repository.FavoriteMovieRepositoryImpl
import com.example.bestapplication.repository.MovieRepository
import com.example.bestapplication.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
internal object DataModule {

    @Singleton
    @Provides
    fun provideRepository(api: NetworkApi): MovieRepository {
        return MoviesRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideFavoriteMovieRepository(
        dao: MovieDao,
        api: NetworkApi
    ): FavoriteMovieRepository {
        return FavoriteMovieRepositoryImpl(dao, api)
    }
}