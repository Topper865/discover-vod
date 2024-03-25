package dev.sajidali.gflix.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import dev.sajidali.gflix.model.ContentType
import dev.sajidali.gflix.model.MenuItemModel
import dev.sajidali.gflix.ui.Menu
import dev.sajidali.gflix.ui.main.backdrop.BackdropScreen
import dev.sajidali.gflix.ui.main.home.HomeViewModel
import dev.sajidali.gflix.ui.main.movies.MoviesViewModel
import dev.sajidali.gflix.ui.main.shows.ShowsViewModel
import dev.sajidali.gflix.ui.vod.Content

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainScreen(mainNavController: NavController) {

    val localNavController = rememberNavController()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Box(modifier = Modifier) {
            NavHost(navController = localNavController, startDestination = "home") {
                composable("search") {
                    Content(type = ContentType.MOVIE)
                }
                composable("home") {
                    BackdropScreen(
                        viewModel = viewModel<HomeViewModel>(),
                        mainNavController = mainNavController
                    )
                }
                composable("movies") {
                    BackdropScreen(
                        viewModel = viewModel<MoviesViewModel>(),
                        mainNavController = mainNavController
                    )
                }
                composable("tv_shows") {
                    BackdropScreen(
                        viewModel = viewModel<ShowsViewModel>(),
                        mainNavController = mainNavController
                    )
                }
                composable("settings") {
                    Content(ContentType.EPISODE)
                }
            }

            Menu(
                listOf(
                    MenuItemModel(1, "Search"),
                    MenuItemModel(2, "Home"),
                    MenuItemModel(3, "Movies"),
                    MenuItemModel(4, "TV Shows"),
                    MenuItemModel(5, "Settings"),
                ),
                onSelected = { item ->
                    localNavController.navigate(item.route)
                },
                initialSelected = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 16.dp)
            )
        }
    }
}