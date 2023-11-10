package dev.sajidali.gflix.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.sajidali.gflix.R
import dev.sajidali.gflix.model.Movie

@Composable
fun Content() {
    Box(modifier = Modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(5), content = {
                items(20) {
                    ContentCard(movie = Movie(it, "Description", "https://picsum.photos/240/360"))
                }
            }, contentPadding = PaddingValues(16.dp),
            modifier = Modifier
        )
    }
}

@Composable
fun ContentCard(movie: Movie) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    val cardShape = RoundedCornerShape(5)
    Card(
        shape = CardDefaults.shape,
        modifier = Modifier
            .padding(8.dp)
            .height(260.dp)
            .focusRequester(FocusRequester.Default)
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .focusable()
            .animateContentSize()
    ) {
        AsyncImage(
            model = movie.poster,
            placeholder = painterResource(id = R.drawable.poster),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Preview(device = Devices.TV_720p, showSystemUi = true)
@Preview(device = Devices.TABLET, showSystemUi = true)
fun ContentPreview() {
    Content()
}