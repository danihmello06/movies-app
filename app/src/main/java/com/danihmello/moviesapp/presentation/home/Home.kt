package com.danihmello.moviesapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.danihmello.moviesapp.data.Movie
import com.danihmello.moviesapp.data.MovieResumed
import com.danihmello.moviesapp.presentation.theme.grayBackground
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
//    val viewModel: HomeViewModel = viewModel()
//    val state = viewModel.state.value

    val movies = homeViewModel.state.collectAsState().value
    val upcomingMovies = movies.upComingMovies.take(8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(grayBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            upcomingMovies.size
        }

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(40.dp)
        ) { index ->
            CardContent(index, pagerState, upcomingMovies, navController)
        }
        PagerIndicator(pagerState = pagerState)
    }
}

@Composable
fun PagerIndicator(pagerState: PagerState) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until pagerState.pageCount) {
            val isSelected = i == pagerState.currentPage
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray)
                    .size(if (isSelected) 8.dp else 4.dp)
            )
        }
    }
}

@Composable
fun CardContent(
    index: Int,
    pagerState: PagerState,
    highlightMovies: List<MovieResumed>,
    navController: NavHostController
) {
    val pagerOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .graphicsLayer {
                lerp(
                    start = 0.88f.dp,
                    stop = 1f.dp,
                    fraction = 1f - pagerOffset.absoluteValue.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale.value
                    scaleY = scale.value
                }
                alpha = lerp(
                    start = 0.5f.dp,
                    stop = 1f.dp,
                    fraction = 1f - pagerOffset.absoluteValue.coerceIn(0f, 1f)
                ).value
            }
            .clickable {
                navController.navigate("details/${highlightMovies[index].id}")
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .height(480.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(highlightMovies[index].posterFullLink)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
    }
}

