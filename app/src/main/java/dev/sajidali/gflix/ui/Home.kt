package dev.sajidali.gflix.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.sajidali.gflix.model.Movie
import dev.sajidali.gflix.ui.theme.GFlixTheme

@Composable
fun Home() {
    Column(modifier = Modifier) {
        Spacer(modifier = Modifier.weight(1f))
        HomeContent(modifier = Modifier.weight(1f))
    }
}

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    LazyColumn(
        content = {
            item {
                ContentRow(
                    rowTitle = "Continue Watching",
                    content = dev.sajidali.gflix.utils.listOf(20) {
                        Movie(
                            1,
                            "Title",
                            "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg"
                        )
                    },
                    modifier = Modifier.height(260.dp)
                )
            }
            item {
                ContentRow(
                    rowTitle = "Favorites",
                    content = dev.sajidali.gflix.utils.listOf(20) {
                        Movie(
                            1,
                            "Title",
                            "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg"
                        )
                    },
                    modifier = Modifier.height(260.dp)
                )
            }
        },
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
//        pivotOffsets = PivotOffsets(0.16f, 0f)
    )
}


@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewHome() {
    GFlixTheme {
        Home()
    }
}