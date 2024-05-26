package com.example.tddKotlin

import com.example.tddKotlin.model.Bird
import com.example.tddKotlin.model.magamo
import com.example.tddKotlin.model.suzume
import com.example.tddKotlin.model.tsubame

class ExampleRepository {
    suspend fun getBirds(): List<Bird> {
        return listOf(suzume, tsubame, magamo)
    }
}
