package com.example.bestapplication.utilites

import com.example.bestapplication.favorite_movie.entity.FavoriteMovie

sealed class MoviesResult
class ValidResult(val result: List<FavoriteMovie>) : MoviesResult()
object EmptyResult : MoviesResult()
object EmptyQuery : MoviesResult()
class ErrorResult(val e: Throwable) : MoviesResult()


