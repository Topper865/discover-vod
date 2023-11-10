package dev.sajidali.gflix.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.sajidali.gflix.R
import dev.sajidali.gflix.ui.theme.GFlixTheme


@Composable
fun DetailsHeader(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 32.sp,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DetailsContent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        AsyncImage(
            model = "https://picsum.photos/240/360",
            contentDescription = "",
            placeholder = painterResource(
                id = R.drawable.poster
            ),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1.2f)
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(3f)
        ) {
            Text(text = "2018-12-19", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n" +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five",
                fontSize = 16.sp
            )
        }

        Column(modifier = Modifier.weight(1.5f)) {
            InfoColumn(header = "Directed By", text = "Bob Marshal")
            Spacer(modifier = Modifier.height(8.dp))
            InfoColumn(header = "Genre", text = "Fantasy/Family/Comedy")
            Spacer(modifier = Modifier.height(8.dp))
            InfoColumn(
                header = "Starring",
                text = "Emily Blunt, Lin Manueul, Miranda, Ben Whishaw, Emily Mortimer, Pixie Davies, Nathanal Saleh, Joel Dawson, Julie Walters"
            )
        }
    }
}

@Composable
fun InfoColumn(header: String, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Text(text = header, fontSize = 16.sp)
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold)

    }
}

@Composable
fun ActionButtons(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        ActionButton(text = "Watch Now", modifier = Modifier.weight(1.2f))
        Spacer(modifier = Modifier.weight(1f))
        ActionButton(text = "Watch Trailer", modifier = Modifier.weight(1.1f))
        Spacer(modifier = Modifier.weight(0.2f))
        ActionButton(text = "Add to my List", modifier = Modifier.weight(1.2f))
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ActionButton(text: String, modifier: Modifier = Modifier) {
    var textSize by remember {
        mutableIntStateOf(20)
    }
    var focused by remember {
        mutableStateOf(false)
    }
    Button(onClick = { /*TODO*/ },
        shape = ButtonDefaults.shape,
        colors = ButtonDefaults.buttonColors(),
        modifier = modifier
            .onFocusChanged { focused = it.isFocused }

    ) {
        Text(text = text, fontWeight = FontWeight.SemiBold, fontSize = textSize.sp, onTextLayout = {
            if (it.lineCount > 1) {
                textSize -= 1
            }
        })
    }
}


@Preview(device = Devices.TV_720p, showSystemUi = true)
@Composable
fun PreviewDetails() {
    GFlixTheme {
        Box(modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)) {
            Column(modifier = Modifier) {
                DetailsHeader("MOONLIGHT", modifier = Modifier.weight(0.5f))
                DetailsContent(
                    modifier = Modifier
                        .weight(2f)
                        .padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                ActionButtons(modifier = Modifier.weight(1f))
            }
        }
    }
}