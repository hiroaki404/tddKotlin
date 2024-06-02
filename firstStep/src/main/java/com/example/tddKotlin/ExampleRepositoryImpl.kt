package com.example.tddKotlin

import com.example.tddKotlin.model.Bird
import com.example.tddKotlin.model.magamo
import com.example.tddKotlin.model.suzume
import com.example.tddKotlin.model.tsubame

interface ExampleRepository {
    suspend fun getBirds(): List<Bird>
}

class ExampleRepositoryImpl : ExampleRepository {
    override suspend fun getBirds(): List<Bird> {
        return listOf(suzume, tsubame, magamo)
    }
}
