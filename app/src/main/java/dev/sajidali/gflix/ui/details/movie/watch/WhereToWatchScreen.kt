package dev.sajidali.gflix.ui.details.movie.watch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListItemScope
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.MaterialTheme
import app.moviebase.tmdb.model.TmdbProvider
import coil.compose.AsyncImage
import dev.sajidali.gflix.R
import dev.sajidali.gflix.ui.Sections
import dev.sajidali.gflix.ui.details.movie.MovieDetailsViewModel
import dev.sajidali.gflix.utils.logoUrl

@Composable
fun WhereToWatchScreen(id: Int, viewModel: MovieDetailsViewModel) {

    val watchProviders by viewModel.watchProviders.collectAsState()

    Sections(
        sections = watchProviders, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        ProviderCard(provider = it, onFocused = {})
    }

    if (id > 0)
        viewModel.loadMovieDetails(id)

}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvLazyListItemScope.ProviderCard(
    provider: TmdbProvider,
    onFocused: () -> Unit = {}
) {

    var focusedState by remember {
        mutableStateOf(false)
    }

    Card(
        onClick = { /*TODO*/ },
        border = CardDefaults.border(
            focusedBorder = Border(
                BorderStroke(
                    3.dp,
                    MaterialTheme.colorScheme.primary
                )
            )
        ),
        glow = CardDefaults.glow(
            focusedGlow = Glow(
                MaterialTheme.colorScheme.primary,
                2.dp
            )
        ),
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp / 5f)
            .padding(horizontal = 8.dp)
            .onFocusChanged {
                focusedState = it.isFocused
                onFocused()
            }
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = provider.logoPath.logoUrl(),
                placeholder = painterResource(id = R.drawable.person),
                error = painterResource(id = R.drawable.person),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f, true)
            )
        }
    }

}