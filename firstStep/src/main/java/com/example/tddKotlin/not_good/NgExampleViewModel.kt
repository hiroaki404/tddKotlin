package com.example.tddKotlin.not_good

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tddKotlin.NgExampleRepositoryImpl
import com.example.tddKotlin.model.Bird
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NgExampleUiState(
    val birds: List<Bird> = emptyList()
)

class NgExampleViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NgExampleUiState())
    val uiState: StateFlow<NgExampleUiState> = _uiState.asStateFlow()

    private val repository: NgExampleRepositoryImpl = NgExampleRepositoryImpl()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    birds = repository.getBirds()
                )
            }
        }
    }
}
