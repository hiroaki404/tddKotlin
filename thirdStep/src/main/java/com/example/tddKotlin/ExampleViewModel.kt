package com.example.tddKotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tddKotlin.model.Bird
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

// sealed classでLoadingとSuccessを定義しても良い
data class ExampleUiState(
    val loading : Boolean = true,
    val birds: List<Bird> = emptyList()
)

@HiltViewModel
class ExampleViewModel @Inject constructor(
    repository: ExampleRepository
) : ViewModel() {
    val uiState: StateFlow<ExampleUiState> = repository.birdsFlow
        .map {
            ExampleUiState(loading = false, birds = it)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ExampleUiState()
        )
}
