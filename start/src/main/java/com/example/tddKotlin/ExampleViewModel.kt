package com.example.tddKotlin

import androidx.lifecycle.ViewModel
import com.example.tddKotlin.model.Bird
import com.example.tddKotlin.model.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ExampleUiState(
    val selectedBird: Bird? = null,
    val selectedSeason: Season? = null,
    val birds: List<Bird> = emptyList()
)

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repository: ExampleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExampleUiState())
    val uiState: StateFlow<ExampleUiState> = _uiState.asStateFlow()

    init {
        getBirds()
    }


    private fun getBirds() {
        _uiState.update {
            it.copy(
                birds = repository.getBirds()
            )
        }
    }
}
