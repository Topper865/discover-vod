package dev.sajidali.gflix.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.moviebase.tmdb.Tmdb3
import app.moviebase.tmdb.model.TmdbTimeWindow
import dev.sajidali.gflix.model.SectionModel
import dev.sajidali.gflix.ui.BaseViewModel
import dev.sajidali.gflix.ui.main.backdrop.BackdropViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : BackdropViewModel() {


    override fun updateSections() {
        viewModelScope.launch {
            val trendingMovies = tmdb.trending.getTrendingMovies(TmdbTimeWindow.DAY, 1)
            val trendingShows = tmdb.trending.getTrendingShows(TmdbTimeWindow.DAY, 1)
            _sections.value = listOf(
                SectionModel("Trending Movies", trendingMovies.results),
                SectionModel("Trending Shows", trendingShows.results)
            )
        }
    }

}