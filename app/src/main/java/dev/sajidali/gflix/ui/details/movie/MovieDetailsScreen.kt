package dev.sajidali.gflix.ui.details.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import dev.sajidali.gflix.model.MenuItemModel
import dev.sajidali.gflix.ui.DetailsHeader
import dev.sajidali.gflix.ui.Menu
import dev.sajidali.gflix.ui.details.movie.credits.CreditsScreen
import dev.sajidali.gflix.ui.details.movie.overview.MovieOverviewScreen
import dev.sajidali.gflix.ui.details.movie.watch.WhereToWatchScreen
import dev.sajidali.gflix.ui.theme.GFlixTheme
import dev.sajidali.gflix.ui.theme.Grey66
import dev.sajidali.gflix.utils.url

@Composable
fun MovieDetailsScreen(
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = viewModel()
) {
    val details by viewModel.movieDetails.collectAsState()

    val localNavController = rememberNavController()

    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = details?.backdropImage?.url(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .background(Grey66)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DetailsHeader(details?.title ?: "On The Bus", modifier = Modifier)
            Spacer(modifier = Modifier.height(16.dp))
            Menu(
                listOf(
                    MenuItemModel(1, "Overview"),
                    MenuItemModel(2, "Credits"),
                    MenuItemModel(3, "Where to Watch"),
                    MenuItemModel(4, "More Like This"),
                ),
                onSelected = {
                    localNavController.navigate("${it.route}/$id") {
                        popUpTo("overview/$id") {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
            )

            NavHost(navController = localNavController, startDestination = "overview/$id") {
                composable(
                    "overview/{id}",
                    arguments = listOf(
                        navArgument("id") {
                            type = NavType.IntType
                        }
                    )
                ) {
                    MovieOverviewScreen(viewModel = viewModel, id = it.arguments?.getInt("id") ?: 0)
                }

                composable("credits/{id}",
                    arguments = listOf(
                        navArgument("id") {
                            type = NavType.IntType
                        }
                    )
                ) {
                    CreditsScreen(id = it.arguments?.getInt("id") ?: 0, viewModel = viewModel)
                }

                composable("where_to_watch/{id}") {
                    WhereToWatchScreen(id = it.arguments?.getInt("id") ?: 0, viewModel = viewModel)
                }

                composable("more_like_this/{id}") {

                }
            }
//            Spacer(modifier = Modifier.height(16.dp))
//            ActionButtons(modifier = Modifier.weight(1f))
        }
    }
    viewModel.loadMovieDetails(id)
}

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewMovieDetails() {
    GFlixTheme { MovieDetailsScreen(550) }
}