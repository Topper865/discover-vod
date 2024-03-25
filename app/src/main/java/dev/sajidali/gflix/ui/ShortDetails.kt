package dev.sajidali.gflix.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import dev.sajidali.gflix.ui.theme.GFlixTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ShortDetails(
    header: String,
    overview: String,
    modifier: Modifier = Modifier,
    subHeader: @Composable RowScope.() -> Unit = {},
    note: @Composable ColumnScope.() -> Unit = {},
) {
    Column(modifier = modifier) {
        Text(
            text = header,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            subHeader()
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = overview,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4,
            fontSize = 14.sp,
            lineHeight = 16.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        note()
    }

}

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview(device = Devices.TV_720p, showSystemUi = true)
@Composable
fun PreviewShortDetails() {
    GFlixTheme {
        ShortDetails(
            "On the Buses",
            "On the Buses is a British television sitcom that was broadcast on ITV from 1969 to 1973." +
                    " It was created by Ronald Chesney and Ronald Wolfe, who wrote most of the episodes. " +
                    "It spawned three spin-off feature films and a stage version. Despite the writers' " +
                    "previous successes with The Rag Trade and Meet the Wife with the BBC, the corporation " +
                    "rejected On the Buses, not seeing much comedy potential in a bus depot as a setting. " +
                    "The comedy partnership turned to a friend, Frank Muir, Head of Entertainment at London " +
                    "Weekend Television, who loved the idea; the show was accepted and despite a poor critical " +
                    "reception became a hit with viewers. It was produced by London Weekend Television for the ITV " +
                    "network.",
            subHeader = {
                Text(
                    text = "Comedy",
                    modifier = Modifier
                )
                Text(
                    text = "1969",
                    modifier = Modifier
                )
                Text(
                    text = "7+",
                    modifier = Modifier
                )
                Text(
                    text = "Action,Comedy,Adventure",
                    modifier = Modifier
                )
            },
            note = {
                Text(
                    text = "This is a note",
                    modifier = Modifier
                )
            },
            modifier = Modifier.width(200.dp)
        )
    }
}