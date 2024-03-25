package dev.sajidali.gflix.ui.main.shows

import androidx.lifecycle.viewModelScope
import dev.sajidali.gflix.model.SectionModel
import dev.sajidali.gflix.ui.main.backdrop.BackdropViewModel
import kotlinx.coroutines.launch

class ShowsViewModel : BackdropViewModel() {

    override fun updateSections() {
        viewModelScope.launch {
            val popularPage1 = tmdb.show.popular(1)
            val popularPage2 = tmdb.show.popular(2)
            _sections.value = listOf(
                SectionModel("Popular", popularPage1.results),
                SectionModel("Now Playing", popularPage2.results)
            )
        }
    }

}