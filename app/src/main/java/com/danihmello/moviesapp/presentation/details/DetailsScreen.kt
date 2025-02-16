package com.danihmello.moviesapp.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.danihmello.moviesapp.data.Movie
import com.danihmello.moviesapp.presentation.home.HomeViewModel
import com.danihmello.moviesapp.presentation.theme.grayBackground
import com.danihmello.moviesapp.presentation.theme.grayTransparent

@Composable
fun DetailsScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    movieId: Int,
) {

    viewModel.getMovieDetails(movieId)

    val state = viewModel.state

    val details = state.collectAsState().value.detailsMovie

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        BackGroundPoster(details = details)
        ForegroundPoster(details = details)
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = details.title ?: "",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 38.sp,
                color = Color.White,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )
            Rating(details = details, modifier = Modifier)
            Text(
                text = "Summary",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                color = Color.White,
                lineHeight = 24.sp,
                textAlign = TextAlign.Left
            )
            Text(
                text = details.overview ?: "",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = Color.White,
                lineHeight = 20.sp,
                textAlign = TextAlign.Left
            )
            //ImageRow(details = details)
        }
    }
}

//@Composable
//fun ImageRow(details: Movie) {
//    if (details.images.isNotEmpty()) {
//        LazyRow {
//            items(details.images.size) {
//                AsyncImage(
//                    model = details.images[it], contentDescription = "",
//                    Modifier
//                        .padding(6.dp)
//                        .height(70.dp)
//                        .clip(RoundedCornerShape(12.dp)),
//                    contentScale = ContentScale.Crop
//                )
//            }
//        }
//    }
//}

@Composable
fun TextBuilder(icon: ImageVector, title: String, bodyText: String) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = "Person",
            tint = Color.White
        )
        Text(
            text = title,
            Modifier.padding(start = 10.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
    Text(text = bodyText, color = Color.White)
}

@Composable
fun Rating(details: Movie, modifier: Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Icon(imageVector = Icons.Filled.Star, contentDescription = "", tint = Color.White)
        Text(
            text = details.voteAverage.toString(),
            modifier.padding(start = 6.dp),
            color = Color.White
        )
        Spacer(modifier = modifier.width(25.dp))
//        Icon(
//            painter = painterResource(id = R.drawable.time_24),
//            contentDescription = "",
//            tint = Color.White
//        )
//        Text(
//            text = details.runtime,
//            modifier.padding(start = 6.dp),
//            color = Color.White
//        )
        Spacer(modifier = modifier.width(25.dp))
        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "", tint = Color.White)
        Text(
            text = details.releaseDate ?: "",
            modifier.padding(start = 6.dp),
            color = Color.White
        )
    }
}

@Composable
fun ForegroundPoster(details: Movie) {

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .width(250.dp)
            .padding(top = 40.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = details.posterFullLink, contentDescription = details.title,
            Modifier
                .width(250.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .width(250.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xB91A1B1B),
                        )
                    ), shape = RoundedCornerShape(16.dp)
                )
        )
    }
}

@Composable
fun BackGroundPoster(details: Movie) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(grayBackground)
    ) {
        AsyncImage(
            model = details.posterFullLink, contentDescription = details.title,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.4f)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            grayTransparent,
                            grayBackground
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }
}