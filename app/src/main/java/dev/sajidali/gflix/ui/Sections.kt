package dev.sajidali.gflix.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyListItemScope
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.sajidali.gflix.model.SectionModel
import dev.sajidali.gflix.ui.theme.GFlixTheme

@Composable
fun <T : Any> Sections(
    sections: List<SectionModel<T>>,
    modifier: Modifier = Modifier,
    contentCard: @Composable TvLazyListItemScope.(item: T) -> Unit,
) {
    TvLazyColumn(
        content = {
            items(sections) {
                SectionItem(
                    rowTitle = it.title,
                    content = it.content,
                    modifier = Modifier.fillMaxHeight(),
                    contentCard = contentCard,
                )
            }
        },
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
        pivotOffsets = PivotOffsets(0.20f, 0f)
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun <T> SectionItem(
    rowTitle: String,
    content: List<T>,
    modifier: Modifier = Modifier,
    contentCard: @Composable TvLazyListItemScope.(item: T) -> Unit,
) {

    Box(
        modifier = modifier.padding(vertical = 10.dp)
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Text(text = rowTitle, fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.height(10.dp))
            TvLazyRow(
                content = {
                    itemsIndexed(content) { index, item ->
                        contentCard(item)
                    }
                },
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 32.dp
//                    end = LocalConfiguration.current.screenWidthDp.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                pivotOffsets = PivotOffsets(0.02f, 0f),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewRow() {
    GFlixTheme {

    }
}