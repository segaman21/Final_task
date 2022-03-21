package com.example.bestapplication.di

import android.content.Context
import androidx.room.Room
import com.example.bestapplication.repositories.MovieRepository
import com.example.data.MoviesRepositoryImp
import com.example.data.database.FavoriteMovieDatabase
import com.example.data.network.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }

    @Singleton
    @Provides
    fun getRepository(repository: MoviesRepositoryImp): MovieRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavoriteMovieDatabase::class.java,
        "animals_database"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: FavoriteMovieDatabase) = db.movieDao()

}