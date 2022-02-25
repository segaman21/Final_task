package com.example.bestapplication.ui.movie_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bestapplication.ui.movie_details.FragmentMoviesDetails
import com.example.bestapplication.R
import com.example.bestapplication.SwitchTheme
import com.example.bestapplication.data.model.Genre
import com.example.bestapplication.data.model.MoviePreview
import com.example.bestapplication.databinding.FragmentMoviesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_list.*


@AndroidEntryPoint
class FragmentMoviesList : Fragment(), MovieListAdapter.Callback {
    private var switchTheme = SwitchTheme()
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var genreList = listOf<Genre>()
    private val viewModel by viewModels<MovieListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getGenres()
        initObservers()
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            switchTheme.switch(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel.filmsLiveData.observe(viewLifecycleOwner, { movies ->
            val moviesListAdapter = MovieListAdapter(movies, genreList)
            moviesListAdapter.initCallback(this)
            movies_list.adapter = moviesListAdapter
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                movies_list.layoutManager = GridLayoutManager(context, 2)
            } else {
                movies_list.layoutManager = GridLayoutManager(context, 4)
            }
        })

        viewModel.genresLiveData.observe(viewLifecycleOwner, {
            it ?: return@observe
            genreList = it
            viewModel.getFilms()
        })
    }

    override fun startMovieDetailsFragment(item: MoviePreview) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, FragmentMoviesDetails.newInstance(item.id))
            ?.addToBackStack(null)
            ?.commit()
    }
}


