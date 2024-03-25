package dev.sajidali.gflix.ui.vod

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.MaterialTheme
import app.moviebase.tmdb.model.TmdbMediaListItem
import coil.compose.AsyncImage
import dev.sajidali.gflix.R
import dev.sajidali.gflix.model.ContentType
import dev.sajidali.gflix.utils.poster

@Composable
fun Content(type: ContentType, viewModel: ContentViewModel = viewModel()) {

    val content by viewModel.content.collectAsState()

    Box(modifier = Modifier) {
        TvLazyVerticalGrid(
            columns = TvGridCells.Fixed(5), content = {
                items(content) {
                    ContentCard(item = it)
                }
            }, contentPadding = PaddingValues(16.dp),
            pivotOffsets = PivotOffsets(0.03f, 0f),
            modifier = Modifier
        )
    }

    when (type) {
        ContentType.MOVIE -> viewModel.updateMovies()
        ContentType.TV -> viewModel.updateTvShows()
        ContentType.EPISODE -> viewModel.updateShowEpisodes()
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ContentCard(item: TmdbMediaListItem) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    val cardShape = RoundedCornerShape(10)
    Card(onClick = {},
        shape = CardDefaults.shape(shape = cardShape),
        border = CardDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(
                    2.dp,
                    MaterialTheme.colorScheme.primary
                ), shape = cardShape
            )
        ),
        glow = CardDefaults.glow(
            focusedGlow = Glow(elevationColor = Color.Red, elevation = 5.dp),
        ),
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(2 / 3f)
            .onFocusChanged {
                isFocused = it.isFocused
            }
    ) {
        AsyncImage(
            model = item.poster(),
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
    Content(ContentType.MOVIE)
}