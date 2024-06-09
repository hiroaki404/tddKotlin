package com.example.tddKotlin

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
    private val repository = ExampleRepositoryImpl()

    private val exampleViewModel= ExampleViewModel(repository)

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `初期状態はローディング状態となる`() = runTest {
        assertEquals(
            ExampleUiState(
                loading = true,
                birds = emptyList()
            ),
            exampleViewModel.uiState.value
        )
    }

    @Test
    fun `鳥の一覧を取得できる`() = runTest {
        exampleViewModel.refresh()

        assertEquals(
            ExampleUiState(
                loading = false,
                birds = listOf(
                    suzume,
                    tsubame,
                    magamo
                )
            ),
            exampleViewModel.uiState.value
        )
    }
}
