package dev.sajidali.gflix.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import app.moviebase.tmdb.image.TmdbImage
import app.moviebase.tmdb.image.TmdbImageType
import app.moviebase.tmdb.image.TmdbImageUrlBuilder
import app.moviebase.tmdb.model.TmdbMediaListItem
import java.lang.Math.PI
import java.lang.Math.cos
import java.lang.Math.min
import java.lang.Math.sin
import kotlin.math.pow
import kotlin.math.sqrt

fun <T> listOf(size: Int, block: (position: Int) -> T): List<T> {
    val list = mutableListOf<T>()
    for (i in 0 until size) {
        list.add(block(i))
    }
    return list
}

fun String.logoUrl(): String {
    return TmdbImageUrlBuilder.build(this, TmdbImageType.LOGO, 200, 200)
}

fun TmdbMediaListItem.poster(width: Int = 0): String? {
    return if (width == 0) {
        TmdbImageUrlBuilder.buildPoster(this, 400)
    } else {
        TmdbImageUrlBuilder.buildPoster(this, width)
    }
}

fun TmdbImage.url(): String {
    return when (type) {
        TmdbImageType.BACKDROP -> {
            TmdbImageUrlBuilder.build(image = this, width = 1920, height = 1080)
        }

        TmdbImageType.POSTER -> {
            TmdbImageUrlBuilder.build(image = this, width = 200, height = 300)
        }

        TmdbImageType.PROFILE -> {
            TmdbImageUrlBuilder.build(image = this, width = 100, height = 100)
        }

        else -> {
            TmdbImageUrlBuilder.build(image = this, width = 400, height = 600)

        }
    }
}

fun TmdbMediaListItem.backdrop(width: Int = 0): String? {
    return if (width == 0) {
        TmdbImageUrlBuilder.buildBackdrop(this, 1080)
    } else {
        TmdbImageUrlBuilder.buildPoster(this, width)
    }
}

fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * PI
        val x = cos(angleRad).toFloat() //Fractional x
        val y = sin(angleRad).toFloat() //Fractional y

        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)