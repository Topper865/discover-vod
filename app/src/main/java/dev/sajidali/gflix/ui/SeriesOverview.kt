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
fun SeriesOverview(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.padding(top = 16.dp)) {
//            DetailsContent(modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.height(16.dp))
            ActionButtons(modifier = Modifier.weight(1f))

        }
    }
}

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewSeriesOverview() {
    SeriesOverview()
}