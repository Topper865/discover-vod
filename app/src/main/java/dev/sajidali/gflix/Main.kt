package dev.sajidali.gflix

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.sajidali.gflix.model.MenuItemModel
import dev.sajidali.gflix.ui.Content
import dev.sajidali.gflix.ui.Home
import dev.sajidali.gflix.ui.Menu
import dev.sajidali.gflix.ui.theme.GFlixTheme

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    var background by remember {
        mutableStateOf("https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
//        AsyncImage(
//            model = background,
//            contentDescription = "",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
        Column(modifier = Modifier) {
            Menu(
                listOf(
//                    MenuItemModel(-1, "", R.drawable.ic_search),
                    MenuItemModel(1, "Home"),
                    MenuItemModel(2, "Movies"),
                    MenuItemModel(3, "TV Shows"),
                    MenuItemModel(4, "Settings"),
                ),
                onSelected = { item ->
                    if (item.id == -1) {
                        //   navController.navigate("search")
                    } else {
                        navController.navigate(item.title.lowercase().replace(" ", "_"))
                    }
                },
                modifier = Modifier.padding(16.dp)
            )
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    Home()
                }
                composable("movies") {
                    Content()
                }
                composable("tv_shows") {
                    Content()
                }
                composable("settings") {
                    Content()
                }
            }
        }
    }
}

@Composable
@Preview(device = Devices.TV_1080p, showSystemUi = true)
fun MainScreenPreview() {
    GFlixTheme {
        MainScreen()
    }
}