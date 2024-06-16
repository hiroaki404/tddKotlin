package com.example.tddKotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tddKotlin.model.Bird
import com.example.tddKotlin.model.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

// sealed classでLoadingとSuccessを定義しても良い
data class ExampleUiState(
    val loading: Boolean = true,
    val birds: List<Bird> = emptyList(),
    val selectedSeason: Season?
)

@HiltViewModel
class ExampleViewModel @Inject constructor(
    repository: ExampleRepository
) : ViewModel() {
    private val querySeasonFlow: MutableStateFlow<Season?> = MutableStateFlow(null)

    val uiState: StateFlow<ExampleUiState> = combine(
        querySeasonFlow,
        repository.birdsFlow
    ) { querySeason, birds ->
        if (querySeason == null) {
            birds
        } else {
            birds.filter { it.seasons.contains(querySeason) }
        }
    }.map {
        ExampleUiState(
            loading = it.isEmpty(),
            birds = it,
            selectedSeason = querySeasonFlow.value
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ExampleUiState(selectedSeason = null)
    )

    fun queryBySeason(season: Season) {
        querySeasonFlow.update { season }
    }
}
