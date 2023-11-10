package dev.sajidali.gflix.model

import androidx.annotation.DrawableRes

data class MenuItemModel(val id: Int, val title: String, @DrawableRes val icon: Int = -1)
