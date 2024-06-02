package com.example.tddKotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
@Suppress("RemoveRedundantBackticks", "NonAsciiCharacters")
class ExampleViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

}
