package dev.sajidali.gflix.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import androidx.tv.material3.Text
import dev.sajidali.gflix.model.MenuItemModel
import dev.sajidali.gflix.ui.theme.GFlixTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Menu(
    menu: List<MenuItemModel>,
    modifier: Modifier = Modifier,
    initialSelected: Int = 0,
    onSelected: (item: MenuItemModel) -> Unit = {},
) {

    var selectedTabPosition by remember {
        mutableIntStateOf(initialSelected)
    }

    Row(modifier = modifier) {
        TabRow(
            selectedTabIndex = selectedTabPosition,
            indicator = { tabPositions, doesTabRowHaveFocus ->
                TabRowDefaults.PillIndicator(
                    currentTabPosition = tabPositions[selectedTabPosition],
                    doesTabRowHaveFocus = doesTabRowHaveFocus,
                    activeColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            },
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            menu.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabPosition == index,
                    onFocus = {
                        if (selectedTabPosition != index) {
                            selectedTabPosition = index
                            onSelected(item)
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    TabItem(item = item, selected = selectedTabPosition == index)
                }

            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TabItem(item: MenuItemModel, selected: Boolean = false) {
    if (item.icon != -1) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = "",
            tint = if (selected)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
        )
    }
    if (item.title.isNotEmpty())
        Text(
            item.title,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
}

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewMenu() {
    GFlixTheme {
        Menu(
            listOf(
                MenuItemModel(1, "Home"),
                MenuItemModel(2, "Movies"),
                MenuItemModel(3, "TV Shows"),
                MenuItemModel(4, "My List"),
                MenuItemModel(5, "Settings")
            )
        )
    }
}