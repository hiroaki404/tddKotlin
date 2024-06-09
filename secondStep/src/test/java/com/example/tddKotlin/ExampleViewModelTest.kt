package com.example.tddKotlin

import app.cash.turbine.test
import com.example.tddKotlin.model.magamo
import com.example.tddKotlin.model.suzume
import com.example.tddKotlin.model.tsubame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@Suppress("RemoveRedundantBackticks", "NonAsciiCharacters")
class ExampleViewModelTest {
    private val repository = FakeRepository()

    private lateinit var exampleViewModel: ExampleViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        exampleViewModel = ExampleViewModel(repository)
    }

    @Test
    fun `鳥の一覧を取得できる`() = runTest {
        // Given
        val collectJob = launch(UnconfinedTestDispatcher()) { exampleViewModel.uiState.collect() }

        // When
        repository.emitValue()

        // Then
        assertEquals(
            ExampleUiState(
                birds = listOf(
                    suzume,
                    tsubame,
                    magamo
                )
            ),
            exampleViewModel.uiState.value
        )
        collectJob.cancel()
    }


    @Test
    fun `鳥の一覧を取得できる(turbineを使った場合)`() = runTest {
        exampleViewModel.uiState.test {
            // Given
            skipItems(1)

            // When
            repository.emitValue()

            // Then
            assertEquals(
                ExampleUiState(
                    birds = listOf(
                        suzume,
                        tsubame,
                        magamo
                    )
                ),
                awaitItem()
            )
        }
    }
}
