package com.example.bestapplication.di.modules

import com.example.bestapplication.favorite_movie.usecase.CheckMovieInDatabaseUseCase
import com.example.bestapplication.favorite_movie.usecase.DeleteFromDataBaseUseCase
import com.example.bestapplication.favorite_movie.usecase.GetFavoriteMovieUseCase
import com.example.bestapplication.favorite_movie.usecase.InsertToDataBaseUseCase
import com.example.bestapplication.movie_details.usecase.GetActorUseCase
import com.example.bestapplication.movie_details.usecase.GetMovieDetailsUseCase
import com.example.bestapplication.movie_list.usecase.GetGenreUseCase
import com.example.bestapplication.movie_list.usecase.GetMovieListUseCase
import com.example.bestapplication.repository.FavoriteMovieRepository
import com.example.bestapplication.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object DomainModule {

    @Singleton
    @Provides
    fun provideCheckMovieInDatabaseUseCase(movieRepository: FavoriteMovieRepository) =
        CheckMovieInDatabaseUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideDeleteFromDataBaseUseCase(movieRepository: FavoriteMovieRepository) =
        DeleteFromDataBaseUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetFavoriteMovieUseCase(movieRepository: FavoriteMovieRepository) =
        GetFavoriteMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideInsertToDataBaseUseCase(movieRepository: FavoriteMovieRepository) =
        InsertToDataBaseUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetActorUseCase(movieRepository: MovieRepository) =
        GetActorUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository) =
        GetMovieDetailsUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetGenreUseCase(movieRepository: MovieRepository) =
        GetGenreUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetMovieListUseCase(movieRepository: MovieRepository) =
        GetMovieListUseCase(movieRepository)
}