package com.example.tddKotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tddKotlin.model.Bird
import com.example.tddKotlin.model.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExampleUiState(
    val loading : Boolean,
    val selectedSeason: Season?,
    val birds: List<Bird>
)

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repository: ExampleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExampleUiState(
        loading = true,
        selectedSeason = null,
        birds = emptyList()
    ))
    val uiState: StateFlow<ExampleUiState> = _uiState.asStateFlow()

    fun initialize() {
        viewModelScope.launch {
            repository.birdsFlow
                .map { birds ->
                    birds.toMutableList().filter { bird ->
                        if (_uiState.value.selectedSeason == null) {
                            return@filter true
                        }
                        bird.season.contains(uiState.value.selectedSeason)
                    }
                }.collect { birds ->
                    _uiState.update {
                        it.copy(
                            loading = false,
                            birds = birds
                        )
                    }
                }
        }
    }

    suspend fun selectSeason(season: Season) {
        _uiState.update {
            it.copy(
                selectedSeason = season,
            )
        }
    }
}
