package com.example.tddKotlin

import com.example.tddKotlin.model.Bird
import com.example.tddKotlin.model.magamo
import com.example.tddKotlin.model.suzume
import com.example.tddKotlin.model.tsubame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

interface ExampleRepository {
    val birdsFlow: Flow<List<Bird>>
}

class ExampleRepositoryImpl : ExampleRepository {
    override val birdsFlow: Flow<List<Bird>> = flow {
        // 実際はDataSourceから取得する
        emit(
            listOf(
                suzume,
                tsubame,
                magamo
            )
        )
    }
}

// 実際にはテストモジュールなどへの配置を推奨
class FakeRepository : ExampleRepository {
    private val flow = MutableSharedFlow<List<Bird>>()

    override val birdsFlow: Flow<List<Bird>> = flow

    suspend fun emitValue() {
        flow.emit(
            listOf(
                suzume,
                tsubame,
                magamo
            )
        )
    }
}
