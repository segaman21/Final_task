package com.example.bestapplication.ui.movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bestapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_movies_details)
         }
}