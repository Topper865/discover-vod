package dev.sajidali.gflix.ui.details.movie.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.sajidali.gflix.ui.InfoColumn
import dev.sajidali.gflix.ui.details.movie.MovieDetailsViewModel
import dev.sajidali.gflix.ui.theme.GFlixTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieOverviewScreen(id: Int, viewModel: MovieDetailsViewModel) {

    val details by viewModel.movieDetails.collectAsState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(3f)
        ) {
            Text(
                text = details?.overview ?: "",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Column(modifier = Modifier.weight(1.5f)) {
            InfoColumn(
                header = "Status",
                text = details?.status?.value ?: "N/A"
            )
            Spacer(modifier = Modifier.height(8.dp))
            InfoColumn(header = "Release Date", text = details?.releaseDate?.toString() ?: "N/A")
            Spacer(modifier = Modifier.height(8.dp))
            InfoColumn(
                header = "Genre",
                text = details?.genres?.joinToString(separator = ",") { it.name } ?: "N/A")
            Spacer(modifier = Modifier.height(8.dp))
            InfoColumn(
                header = "Runtime", text = details?.runtime?.let {
                    "${it / 60}h ${it % 60}m"
                } ?: "N/A")
            Spacer(modifier = Modifier.height(8.dp))
            InfoColumn(
                header = "Rating)",
                text = "${details?.voteAverage ?: "N/A"}/10 (${details?.voteCount ?: 0})"
            )
            Spacer(modifier = Modifier.height(8.dp))
            InfoColumn(header = "Language", text = details?.originalLanguage ?: "N/A")

        }
    }

    if (id > 0)
        viewModel.loadMovieDetails(id)

}

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewMovieOverview() {
    GFlixTheme {
        MovieOverviewScreen(550, viewModel())
    }
}