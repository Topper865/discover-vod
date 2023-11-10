package dev.sajidali.gflix.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import dev.sajidali.gflix.R
import dev.sajidali.gflix.model.Movie
import dev.sajidali.gflix.ui.theme.GFlixTheme

@Composable
fun ContentRow(rowTitle: String, content: List<Movie>, modifier: Modifier = Modifier) {

    var isFocusedState by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
    ) {
        Column(modifier = Modifier) {
            Text(text = rowTitle, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                content = {
                    itemsIndexed(content) { index, movie ->
                        HorizontalCard(
                            movie = movie,
                            onFocused = { isFocused ->
                                isFocusedState = isFocused
                            })
                    }
                    item {
                        Spacer(modifier = Modifier.width((LocalConfiguration.current.screenWidthDp - 240).dp))
                    }
                },
                contentPadding = PaddingValues(8.dp),
                verticalAlignment = Alignment.CenterVertically,
//                pivotOffsets = PivotOffsets(0f, 0f),
                modifier = Modifier
            )
        }
    }
}

@Composable
fun HorizontalCard(
    movie: Movie,
    onFocused: (isFocused: Boolean) -> Unit = {}
) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    val focusRequester = remember {
        FocusRequester()
    }
    val cardShape = RoundedCornerShape(10)
    Card(
        shape = CardDefaults.shape,
        modifier = Modifier
            .scale(if (isFocused) 1.1f else 1f)
            .zIndex(if (isFocused) 1f else 0f)
            .padding(8.dp)
            .width(150.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
                onFocused(it.isFocused)
            }
            .border(
                width = 2.dp,
                color = if (isFocused) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = cardShape
            )
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

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewRow() {
    GFlixTheme {
        ContentRow("Row Title", dev.sajidali.gflix.utils.listOf(20) {
            Movie(1, "Title", "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg")
        }, modifier = Modifier.padding(16.dp))
    }
}