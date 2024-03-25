package dev.sajidali.gflix.ui.details.movie

import androidx.lifecycle.viewModelScope
import app.moviebase.tmdb.model.AppendResponse
import app.moviebase.tmdb.model.TmdbMovieDetail
import app.moviebase.tmdb.model.TmdbProvider
import dev.sajidali.gflix.model.SectionModel
import dev.sajidali.gflix.ui.BaseViewModel
import dev.sajidali.gflix.utils.url
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel : BaseViewModel() {

    data class InfoPair(val name: String, val value: String)

    data class CreditPersonModel(val id: Int, val profile: String, val info: List<InfoPair>)
    data class WatchProviderModel(
        val id: Int,
        val logo: String,
        val name: String,
        val priority: Int
    )

    private var _details: MutableStateFlow<TmdbMovieDetail?> = MutableStateFlow(null)

    val movieDetails: StateFlow<TmdbMovieDetail?>
        get() = _details

    private var _creditsSections: MutableStateFlow<List<SectionModel<CreditPersonModel>>> =
        MutableStateFlow(
            emptyList()
        )

    val creditsSections: StateFlow<List<SectionModel<CreditPersonModel>>>
        get() = _creditsSections

    private var _watchProviders: MutableStateFlow<List<SectionModel<TmdbProvider>>> =
        MutableStateFlow(
            emptyList()
        )

    val watchProviders: StateFlow<List<SectionModel<TmdbProvider>>>
        get() = _watchProviders

    private var _similarMovies: MutableStateFlow<List<SectionModel<TmdbMovieDetail>>> =
        MutableStateFlow(
            emptyList()
        )

    val similarMovies: StateFlow<List<SectionModel<TmdbMovieDetail>>>
        get() = _similarMovies

    fun loadMovieDetails(id: Int) {
        if (_details.value != null) return
        viewModelScope.launch {
            _details.value =
                tmdb.movies.getDetails(
                    id,
                    appendResponses = listOf(AppendResponse.CREDITS, AppendResponse.WATCH_PROVIDERS)
                )
                    .also {
                        val starring = it.credits?.cast?.map { cast ->
                            CreditPersonModel(
                                cast.id, cast.profileImage?.url() ?: "", listOf(
                                    InfoPair("Character", cast.character),
                                    InfoPair("Real Name", cast.originalName),
                                    InfoPair("Gender", cast.gender.name),
                                )
                            )
                        } ?: emptyList()
                        val crew = it.credits?.getSortedCrew()?.map { crew ->
                            CreditPersonModel(
                                id = crew.id, crew.profileImage?.url() ?: "", info = listOf(
                                    InfoPair("Name", crew.originalName),
                                    InfoPair("Job", crew.job),
                                    InfoPair("Gender", crew.gender.name),
                                )
                            )
                        } ?: emptyList()
                        _creditsSections.value = listOf(
                            SectionModel("Starring", starring),
                            SectionModel("Crew", crew),
                        )

                        _watchProviders.value = buildList {
                            it.watchProviders?.results?.get("US")
                                ?.let { providers ->
                                    if (providers.flatrate.isNotEmpty())
                                        add(SectionModel("Flat Rate", providers.flatrate))
                                    if (providers.buy.isNotEmpty())
                                        add(SectionModel("Buy", providers.buy))
                                }
                        }
                    }
        }
    }

    fun loadSimilarMovies(id: Int) {
        if (_similarMovies.value.isNotEmpty()) return
        viewModelScope.launch {
        }
    }

}