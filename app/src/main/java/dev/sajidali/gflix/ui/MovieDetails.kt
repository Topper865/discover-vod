package dev.sajidali.gflix.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MovieDetails(modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(vertical = 16.dp, horizontal = 32.dp)) {
        Column(modifier = Modifier) {
            DetailsHeader("MOONLIGHT", modifier = Modifier.weight(0.5f))
            DetailsContent(
                modifier = Modifier
                    .weight(2f)
                    .padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ActionButtons(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewMovieDetails() {
    MovieDetails()
}