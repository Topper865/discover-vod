package dev.sajidali.gflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.sajidali.gflix.ui.details.movie.MovieDetailsScreen
import dev.sajidali.gflix.ui.main.MainScreen
import dev.sajidali.gflix.ui.theme.GFlixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GFlixTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(mainNavController = navController)
        }
        composable("movie/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) {
            MovieDetailsScreen(id = it.arguments?.getInt("id") ?: 0)
        }
    }
}

@Preview(device = Devices.TV_1080p, showSystemUi = true)
@Composable
fun PreviewAppNavigator() {
    GFlixTheme {
        AppNavigator()
    }
}