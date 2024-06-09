package com.example.tddKotlin

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BirdModule {
    @Singleton
    @Provides
    fun provideRepository(): ExampleRepository {
        return FakeRepository()
    }
}
