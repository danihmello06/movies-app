package com.danihmello.moviesapp.ui.home

import MovieResponse
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danihmello.moviesapp.api.MovieApi
import com.danihmello.moviesapp.data.Movie
import com.danihmello.moviesapp.domain.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

//    private val _state = MutableStateFlow(HomeState())
//    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _state = MutableStateFlow(emptyList<Movie>())
    val state: StateFlow<List<Movie>> = _state.asStateFlow()

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {

            //val movies = movieRepository.getPopularMovies(MovieApi.)

            val movies =  movieUseCase.getPopularMovies() //TODO getMoviesUseCase()
            _state.value = movies
            try {

            } catch (e: Exception) {
                //TODO handle error
            }
        }
    }


}