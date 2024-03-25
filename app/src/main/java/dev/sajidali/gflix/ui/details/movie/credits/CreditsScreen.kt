package dev.sajidali.gflix.ui.details.movie.credits

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListItemScope
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import dev.sajidali.gflix.R
import dev.sajidali.gflix.ui.InfoColumn
import dev.sajidali.gflix.ui.Sections
import dev.sajidali.gflix.ui.details.movie.MovieDetailsViewModel

@Composable
fun CreditsScreen(id: Int, viewModel: MovieDetailsViewModel) {

    val details by viewModel.movieDetails.collectAsState()
    val sections by viewModel.creditsSections.collectAsState()
    var focusedPosition by remember {
        mutableIntStateOf(0)
    }

    Sections(
        sections = sections, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        PersonCard(person = it, onFocused = {})
    }

    if (id > 0)
        viewModel.loadMovieDetails(id)

}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvLazyListItemScope.PersonCard(
    person: MovieDetailsViewModel.CreditPersonModel,
    onFocused: () -> Unit = {}
) {

    var focusedState by remember {
        mutableStateOf(false)
    }

    Card(
        onClick = { /*TODO*/ },
        border = CardDefaults.border(
            focusedBorder = Border(
                BorderStroke(
                    3.dp,
                    MaterialTheme.colorScheme.primary
                )
            )
        ),
        glow = CardDefaults.glow(
            focusedGlow = Glow(
                MaterialTheme.colorScheme.primary,
                2.dp
            )
        ),
        modifier = Modifier
            .fillParentMaxWidth(if (focusedState) 0.4f else 0.2f)
            .height(LocalConfiguration.current.screenHeightDp.dp / 2.5f)
            .padding(horizontal = 8.dp)
            .onFocusChanged {
                focusedState = it.isFocused
                onFocused()
            }
    ) {
        if (focusedState) {
            Row(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = person.profile,
                    placeholder = painterResource(id = R.drawable.person),
                    error = painterResource(id = R.drawable.person),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(2 / 3f, true)
                )
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    person.info.forEach { info ->
                        InfoColumn(header = info.name, text = info.value)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        } else {
            AsyncImage(
                model = person.profile,
                placeholder = painterResource(id = R.drawable.person),
                error = painterResource(id = R.drawable.person),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

//@Preview(device = Devices.TV_1080p, showSystemUi = true)
//@Composable
//fun PreviewCreditsScreen() {
//    GFlixTheme {
//        CreditsScreen(id = 550, viewModel = viewModel())
//    }
//}