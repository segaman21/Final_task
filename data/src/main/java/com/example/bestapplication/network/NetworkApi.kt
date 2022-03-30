package com.example.bestapplication.network

import com.example.bestapplication.model.ActorListApi
import com.example.bestapplication.model.GenreListApi
import com.example.bestapplication.model.MovieFullApi
import com.example.bestapplication.model.MovieListApi
import retrofit2.http.*

interface NetworkApi {
    @Headers("Content-type: application/json")
    @GET("movie/now_playing")
    suspend fun getMovieList(
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): MovieListApi

    @Headers("Content-type: application/json")
    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): ActorListApi

    @Headers("Content-type: application/json")
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): GenreListApi

    @Headers("Content-type: application/json")
    @GET("movie/{movie_id}")
    suspend fun getFullMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): MovieFullApi
}