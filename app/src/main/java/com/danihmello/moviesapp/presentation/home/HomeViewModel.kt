package com.danihmello.moviesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danihmello.moviesapp.domain.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getPopularMovies()
        getUpcomingMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            movieUseCase.getPopularMovies().collect { movieList ->
                _state.update { it.copy(popularMovies = movieList) }
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            movieUseCase.getUpcomingMovies().collect { movieList ->
                _state.update { it.copy(upComingMovies = movieList) }
            }
        }
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            movieUseCase.getMovieDetails(id).collect { movie ->
                _state.update { it.copy(detailsMovie = movie) }
            }
        }
    }
}