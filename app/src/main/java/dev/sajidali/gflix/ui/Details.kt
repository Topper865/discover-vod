@file:OptIn(ExperimentalTvMaterial3Api::class)

package dev.sajidali.gflix.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import dev.sajidali.gflix.ui.theme.GFlixTheme


@Composable
fun DetailsHeader(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun InfoColumn(header: String, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Text(text = header, fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimary)
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )

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
        shape = ButtonDefaults.shape(),
        colors = ButtonDefaults.colors(),
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
                Spacer(modifier = Modifier.height(16.dp))
                ActionButtons(modifier = Modifier.weight(1f))
            }
        }
    }
}