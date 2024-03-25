package dev.sajidali.gflix.ui.vod

import androidx.lifecycle.viewModelScope
import app.moviebase.tmdb.model.TmdbMediaListItem
import dev.sajidali.gflix.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ContentViewModel : BaseViewModel() {

    var _content: MutableStateFlow<List<TmdbMediaListItem>> = MutableStateFlow(emptyList())

    val content: MutableStateFlow<List<TmdbMediaListItem>>
        get() = _content


    fun updateMovies() {
        viewModelScope.launch {
            _content.value = tmdb.movies.popular(1).results
        }
    }

    fun updateTvShows() {
        viewModelScope.launch {
            _content.value = tmdb.show.popular(1).results
        }
    }

    fun updateShowEpisodes(){
        viewModelScope.launch {
        }
    }

}