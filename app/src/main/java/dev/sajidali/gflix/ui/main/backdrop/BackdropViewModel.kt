package dev.sajidali.gflix.ui.main.backdrop

import app.moviebase.tmdb.model.TmdbMediaListItem
import dev.sajidali.gflix.model.SectionModel
import dev.sajidali.gflix.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BackdropViewModel : BaseViewModel() {

    protected var _sections: MutableStateFlow<List<SectionModel<TmdbMediaListItem>>> =
        MutableStateFlow(emptyList())

    val sections: StateFlow<List<SectionModel<TmdbMediaListItem>>>
        get() = _sections


    open fun updateSections() {}

}