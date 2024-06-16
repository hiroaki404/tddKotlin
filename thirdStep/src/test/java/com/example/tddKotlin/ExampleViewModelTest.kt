package com.example.tddKotlin

import app.cash.turbine.test
import com.example.tddKotlin.model.magamo
import com.example.tddKotlin.model.suzume
import com.example.tddKotlin.model.tsubame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun `初期状態はローディング状態となる`() = runTest {
        exampleViewModel.uiState.test {
            assertEquals(
                ExampleUiState(
                    loading = true,
                    birds = emptyList()
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `鳥の一覧を取得できる`() = runTest {
        exampleViewModel.uiState.test {
            // Given
            skipItems(1)

            // When
            repository.emitValue()

            // Then
            assertEquals(
                ExampleUiState(
                    loading = false,
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
