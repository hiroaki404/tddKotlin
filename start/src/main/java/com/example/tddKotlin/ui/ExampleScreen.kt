package com.example.tddKotlin.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tddKotlin.ExampleViewModel
import com.example.tddKotlin.ui.theme.TddKotlinTheme


@Composable
fun ExampleScreen(
    modifier: Modifier = Modifier,
    viewModel: ExampleViewModel = hiltViewModel()
) {
    ExampleScreenContent(modifier = modifier)
}

@Composable
fun ExampleScreenContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Example Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun ExampleScreenPreview() {
    TddKotlinTheme {
        ExampleScreen()
    }
}
