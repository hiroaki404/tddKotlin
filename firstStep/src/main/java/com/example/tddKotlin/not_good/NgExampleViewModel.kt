package com.example.tddKotlin.not_good

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tddKotlin.ExampleRepositoryImpl
import com.example.tddKotlin.model.Bird
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NgExampleUiState(
    val birds: List<Bird> = emptyList()
)

@HiltViewModel
class NgExampleViewModel @Inject constructor(
    private val repository: ExampleRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(NgExampleUiState())
    val uiState: StateFlow<NgExampleUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getBirds()
        }
    }

    private suspend fun getBirds() {
        _uiState.update {
            it.copy(
                birds = repository.getBirds()
            )
        }
    }
}
