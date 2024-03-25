package dev.sajidali.gflix.ui

import androidx.lifecycle.ViewModel
import app.moviebase.tmdb.Tmdb3

abstract class BaseViewModel : ViewModel() {

    protected val tmdb = Tmdb3("Your Own API Key")

}