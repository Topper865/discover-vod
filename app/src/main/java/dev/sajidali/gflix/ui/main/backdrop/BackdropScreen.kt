package dev.sajidali.gflix.ui.main.backdrop

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import app.moviebase.tmdb.model.TmdbMediaListItem
import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbShow
import coil.compose.AsyncImage
import dev.sajidali.gflix.R
import dev.sajidali.gflix.ui.Sections
import dev.sajidali.gflix.ui.ShortDetails
import dev.sajidali.gflix.ui.theme.GFlixTheme
import dev.sajidali.gflix.utils.backdrop
import dev.sajidali.gflix.utils.poster

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun BackdropScreen(
    menuPadding: Dp = 36.dp,
    viewModel: BackdropViewModel,
    mainNavController: NavController
) {

    val sections by viewModel.sections.collectAsState()

    var selectedItem by remember {
        mutableStateOf<TmdbMediaListItem?>(null)
    }

    val onItemSelected = { item: TmdbMediaListItem ->
        selectedItem = item
    }

    Box {
        Crossfade(
            targetState = selectedItem,
            label = "Backdrop.image",
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .fillMaxHeight(0.55f)
                .align(Alignment.TopEnd)
        ) {
            AsyncImage(
                model = it?.backdrop() ?: "",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        0.45f to MaterialTheme.colorScheme.background,
                        0.65f to Color.Transparent,
                    )
                )
                .background(
                    Brush.verticalGradient(
                        0.45f to MaterialTheme.colorScheme.background,
                        0.6f to Color.Transparent,
                        startY = Float.POSITIVE_INFINITY,
                        endY = 0f
                    )
                )
                .background(
                    Brush.verticalGradient(
                        0f to MaterialTheme.colorScheme.background,
                        0.1f to Color.Transparent
                    )
                )
        )

        Column(modifier = Modifier.padding(top = menuPadding)) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxWidth(0.5f)
                    .padding(start = 20.dp, top = 16.dp)
            ) {
                when (selectedItem) {
                    is TmdbMovie -> {
                        (selectedItem as TmdbMovie).let {
                            ShortDetails(
                                header = it.title,
                                overview = it.overview,
                                subHeader = {
                                    Text(text = "${it.releaseDate?.year} | ${it.voteAverage} ⭐️")
                                },
                                modifier = Modifier.fillMaxHeight()
                            )
                        }
                    }

                    is TmdbShow -> {
                        (selectedItem as TmdbShow).let {
                            ShortDetails(
                                header = it.name,
                                overview = it.overview,
                                modifier = Modifier.fillMaxHeight()
                            )
                        }
                    }

                    else -> {

                    }
                }
            }

            Sections<TmdbMediaListItem>(
                sections,
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxWidth()
            ) { item ->
                HorizontalCard(
                    movie = item,
                    onFocused = { isFocused ->
                        if (isFocused) {
                            onItemSelected(item)
                        }
                    },
                    onClicked = {
                        mainNavController.navigate("movie/${item.id}")
                    }
                )
            }
        }
    }

    viewModel.updateSections()

}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HorizontalCard(
    movie: TmdbMediaListItem,
    onFocused: (isFocused: Boolean) -> Unit = {},
    onClicked: () -> Unit = {}
) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    val cardShape = RoundedCornerShape(10)
    Card(
        onClick = onClicked,
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
            .height(LocalConfiguration.current.screenHeightDp.dp / 2.6f)
            .aspectRatio(2 / 3f, true)
            .onFocusChanged {
                isFocused = it.isFocused
                onFocused(it.isFocused)
            }
    ) {
        AsyncImage(
            model = movie.poster(),
            placeholder = painterResource(id = R.drawable.poster),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxHeight()
        )
    }
}

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewBackdropScreen() {
    GFlixTheme {

    }
}