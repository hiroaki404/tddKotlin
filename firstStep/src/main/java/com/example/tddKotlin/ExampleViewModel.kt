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

//    delete this block
//    init {
//        getBirds()
//    }

    suspend fun getBirds() {
        _uiState.update {
            it.copy(
                loading = false,
                birds = repository.getBirds()
            )
        }
    }
}
