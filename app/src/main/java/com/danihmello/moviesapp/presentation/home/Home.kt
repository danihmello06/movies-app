package com.danihmello.moviesapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.danihmello.moviesapp.data.MovieResumed
import com.danihmello.moviesapp.presentation.theme.grayBackground
import kotlin.math.absoluteValue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val moviesState = homeViewModel.state.collectAsState().value
    val upcomingMovies = moviesState.upComingMovies.take(8)


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(grayBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Title("Upcoming")
        }
        item {
            val pagerState = rememberPagerState(initialPage = 0) {
                upcomingMovies.size
            }
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(40.dp, 0.dp, 40.dp, 24.dp)
            ) { index ->
                CardContent(index, pagerState, upcomingMovies, navController)
            }
            PagerIndicator(pagerState = pagerState)
        }
        item {
            Title("Popular")
        }
        item {
            FlowRow(
                maxItemsInEachRow = 2,
                modifier = Modifier
                    .padding(2.dp)
                    .wrapContentWidth(Alignment.Start, unbounded = false)
                    .background(Color.Transparent)
            ) {
                moviesState.popularMovies.forEachIndexed { index, _ ->
                    ItemUi(
                        itemIndex = index,
                        movieList = moviesState.popularMovies,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun Title(textValue: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(40.dp, 40.dp, 0.dp, 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = textValue,
            fontSize = 24.sp,
            color = Color.LightGray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ItemUi(itemIndex: Int, movieList: List<MovieResumed>, navController: NavHostController) {
    Card(
        Modifier
            .fillMaxWidth(0.45f)
            .padding(10.dp)
            .clickable {
                navController.navigate("details/${movieList[itemIndex].id}")
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = movieList[itemIndex].posterFullLink,
                contentDescription = movieList[itemIndex].title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray.copy(.7f)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .padding(start = 8.dp)
                    ,
                    text = movieList[itemIndex].title ?: "",
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.Right

                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "",
                        tint = Color.Yellow,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = movieList[itemIndex].voteAverage.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),

                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 1,
                    )
                }
            }
        }
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
