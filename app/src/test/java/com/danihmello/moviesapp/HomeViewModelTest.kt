package com.danihmello.moviesapp

import com.danihmello.moviesapp.data.MovieResumed
import com.danihmello.moviesapp.domain.MovieUseCase
import com.danihmello.moviesapp.presentation.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import org.junit.Test
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before


class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val mockUseCase: MovieUseCase = mockk()
    private lateinit var viewModel: HomeViewModel

    private val moviesResumedList: List<MovieResumed> =
        listOf(
            MovieResumed(
                id = 1,
                title = "Test Movie",
                posterFullLink = "https://image.tmdb.org/t/p/w500/xi8Iu6qyTfyZVDVy60raIOYJJmk.jpg",
                voteAverage = 8.5
            ),
            MovieResumed(
                id = 1,
                title = "Test Movie",
                posterFullLink = "https://image.tmdb.org/t/p/w500/xi8Iu6qyTfyZVDVy60raIOYJJmk.jpg",
                voteAverage = 8.5
            ),
        )


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        coEvery { mockUseCase.getUpcomingMovies() } returns flowOf(moviesResumedList)
        coEvery { mockUseCase.getPopularMovies() } returns flowOf(moviesResumedList)

        viewModel = HomeViewModel(mockUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun givenHomeViewModel_whenInitialized_thenStateShouldEmitCorrectMovies() = runTest {

        advanceUntilIdle()

        val data = viewModel.state.first()

        assertEquals(moviesResumedList, data.upComingMovies)
        assertEquals(moviesResumedList, data.popularMovies)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}