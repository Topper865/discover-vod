package dev.sajidali.gflix.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.sajidali.gflix.model.MenuItemModel
import dev.sajidali.gflix.ui.theme.GFlixTheme

@Composable
fun SeriesDetails() {
    Box(modifier = Modifier) {
        Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)) {
            DetailsHeader("On the Buses", modifier = Modifier.wrapContentHeight())
            Menu(
                menu = listOf(
                    MenuItemModel(1, "Overview"),
                    MenuItemModel(2, "All Episodes"),
                    MenuItemModel(3, "Season 1"),
                    MenuItemModel(4, "Season 2"),
                    MenuItemModel(5, "Season 3"),
                    MenuItemModel(6, "Season 4"),
                    MenuItemModel(7, "Season 5"),
                    MenuItemModel(8, "Season 6"),
                    MenuItemModel(8, "Season 7"),
                ),
                modifier = Modifier.wrapContentHeight()
            )
            //Replace with NavHost
            SeriesOverview(modifier = Modifier.weight(9f))
        }
    }
}

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun SeriesDetailsPreview() {
    GFlixTheme {
        SeriesDetails()
    }
}