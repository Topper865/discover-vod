package dev.sajidali.gflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.sajidali.gflix.ui.theme.GFlixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GFlixTheme {
                // A surface container using the 'background' color from the theme

            }
        }
    }
}
