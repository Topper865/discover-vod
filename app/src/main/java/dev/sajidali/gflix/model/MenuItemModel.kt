package dev.sajidali.gflix.model

import androidx.annotation.DrawableRes

data class MenuItemModel(
    val id: Int,
    val title: String,
    val route: String = title.lowercase().replace(" ", "_"),
    @DrawableRes val icon: Int = -1
)
