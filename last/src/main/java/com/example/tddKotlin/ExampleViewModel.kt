package com.example.tddKotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tddKotlin.model.Bird
import com.example.tddKotlin.model.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

// sealed classでLoadingとSuccessを定義しても良い
data class ExampleUiState(
    val loading: Boolean = true,
    val birds: List<Bird> = emptyList(),
    val selectedSeason: Season
)

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repository: ExampleRepository
) : ViewModel() {
    private var querySeason: Season? = null

    val uiState: StateFlow<ExampleUiState> = repository.birdsFlow
        .map {
            if (querySeason == null) {
                it
            } else {
                it.filter { it.seasons.contains(querySeason) }
            }
        }
        .map {
            ExampleUiState(loading = false, birds = it, selectedSeason = Season.SPRING)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ExampleUiState(selectedSeason = Season.SPRING)
        )

    suspend fun queryBySeason(season: Season) {
        querySeason = season
        repository.refresh()
    }
}
