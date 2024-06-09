package com.example.tddKotlin

import com.example.tddKotlin.model.Bird
import com.example.tddKotlin.model.magamo
import com.example.tddKotlin.model.suzume
import com.example.tddKotlin.model.tsubame

interface ExampleRepository {
    suspend fun getBirds(): List<Bird>
}

// ngパターン用
class ExampleRepositoryImpl {
    suspend fun getBirds(): List<Bird> {
        TODO("Not yet implemented")
    }
}

// 実際にはテストモジュールなどへの配置を推奨
class FakeRepository : ExampleRepository {
    override suspend fun getBirds(): List<Bird> {
        return listOf(suzume, tsubame, magamo)
    }
}
