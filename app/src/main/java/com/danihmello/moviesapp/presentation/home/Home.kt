package com.danihmello.moviesapp.presentation.home

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import kotlin.math.absoluteValue

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            images.size
        }

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(50.dp)
        ) { index ->
            CardContent(index, pagerState)
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
fun CardContent(index: Int, pagerState: PagerState) {
    val pagerOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .padding(1.dp)
            .graphicsLayer {
                lerp(
                    start = 0.85f.dp,
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
    ) {
        AsyncImage(
            modifier = Modifier
                .height(480.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(images[index])
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
    }
}

val images = listOf(
    "https://image.tmdb.org/t/p/w500/3L3l6LsiLGHkTG4RFB2aBA6BttB.jpg",
    "https://image.tmdb.org/t/p/w500/d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
    "https://image.tmdb.org/t/p/w500/A9ENz6d4lC3UYOX8Z1gJwDEo1sM.jpg",
    "https://image.tmdb.org/t/p/w500/5qGIxdEO841C0tdY8vOdLoRVrr0.jpg",
    "https://image.tmdb.org/t/p/w500/sqiCinCzUGlzQiFytS30N1nO3Pt.jpg",
    "https://image.tmdb.org/t/p/w500/sqiCinCzUGlzQiFytS30N1nO3Pt.jpg",
    "https://image.tmdb.org/t/p/w500/sqiCinCzUGlzQiFytS30N1nO3Pt.jpg",
    "https://image.tmdb.org/t/p/w500/sqiCinCzUGlzQiFytS30N1nO3Pt.jpg"
)


