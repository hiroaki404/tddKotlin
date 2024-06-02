package com.example.tddKotlin

import com.example.tddKotlin.model.Bird
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class ExampleRepository {
    val flow = MutableSharedFlow<List<Bird>>()

    val birdsFlow: Flow<List<Bird>> = flow

    suspend fun emit(value: List<Bird>) {
        flow.emit(value)
    }
}
