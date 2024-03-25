package dev.sajidali.gflix.ui.main.movies

import androidx.lifecycle.viewModelScope
import dev.sajidali.gflix.model.SectionModel
import dev.sajidali.gflix.ui.main.backdrop.BackdropViewModel
import kotlinx.coroutines.launch

class MoviesViewModel : BackdropViewModel() {

    override fun updateSections() {
        viewModelScope.launch {
            val popularPage1 = tmdb.movies.popular(1)
            val popularPage2 = tmdb.movies.popular(2)
            _sections.value = listOf(
                SectionModel("Popular", popularPage1.results),
                SectionModel("Now Playing", popularPage2.results)
            )
        }
    }

}