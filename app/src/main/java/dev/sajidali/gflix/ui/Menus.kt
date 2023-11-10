package dev.sajidali.gflix.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.sajidali.gflix.model.MenuItemModel
import dev.sajidali.gflix.ui.theme.GFlixTheme

@Composable
fun Menu(
    menu: List<MenuItemModel>,
    onSelected: (item: MenuItemModel) -> Unit = {},
    modifier: Modifier = Modifier
) {

    var selectedTabPosition by remember {
        mutableIntStateOf(0)
    }

    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(menu) { index, item ->
            MenuItem(item = item, onSelected = {
                onSelected(it)
                selectedTabPosition = index
            })
        }
    }
}

@Composable
fun MenuItem(
    item: MenuItemModel,
    modifier: Modifier = Modifier,
    onClick: (item: MenuItemModel) -> Unit = {},
    onSelected: (item: MenuItemModel) -> Unit = {},
) {
    var focusState by remember {
        mutableStateOf(false)
    }
    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                if (focusState)
                    MaterialTheme.colorScheme.primary
                else
                    Color.Transparent,
                shape = CircleShape
            )
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .focusRequester(FocusRequester.Default)
            .onFocusChanged {
                focusState = it.isFocused
                if (it.isFocused)
                    onSelected(item)
            }
            .focusable()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            if (item.icon != -1) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = "",
                    tint = if (focusState)
                        MaterialTheme.colorScheme.onPrimary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(22.dp)
                )
            }
            if (item.title.isNotEmpty())
                Text(
                    item.title,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = if (focusState) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
        }
    }
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