package com.example.tddKotlin.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.tddKotlin.ExampleUiState
import com.example.tddKotlin.ExampleViewModel
import com.example.tddKotlin.R
import com.example.tddKotlin.model.Bird
import com.example.tddKotlin.model.Season
import com.example.tddKotlin.model.magamo
import com.example.tddKotlin.model.suzume
import com.example.tddKotlin.model.tsubame
import com.example.tddKotlin.ui.theme.TddKotlinTheme


@Composable
fun ExampleScreen(
    modifier: Modifier = Modifier,
    viewModel: ExampleViewModel = hiltViewModel()
) {
    val model by viewModel.uiState.collectAsState()
    ExampleScreenContent(modifier = modifier, model = model)
}

@Composable
fun ExampleScreenContent(modifier: Modifier = Modifier, model: ExampleUiState) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        if (model.loading) {
            Loading(modifier = Modifier.fillMaxSize())
        } else {
            model.birds.let { birds ->
                LazyColumn {
                    item {
                        SeasonSelector(selectedSeason = null, onSelected = {})
                        HorizontalDivider()
                    }

                    items(birds.size) { index ->
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val bird = birds[index]
                                DescriptionSection(bird = bird)

                                ImageSection(bird = bird)
                            }
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SeasonSelector(selectedSeason: Season?, onSelected: (Season) -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Season.entries.forEach { season ->
            SeasonRadioButton(selectedSeason, season) {
                onSelected(season)
            }
        }
    }
}

@Composable
fun RowScope.SeasonRadioButton(selectedSeason: Season?, season: Season, onSelected: () -> Unit) {
    Text(season.value)
    RadioButton(
        selected = selectedSeason == Season.WINTER,
        onClick = onSelected
    )
}

@Composable
fun DescriptionSection(modifier: Modifier = Modifier, bird: Bird) {
    Column(modifier = modifier) {
        Text(bird.name)
        Text("${bird.height} cm")
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            bird.seasons.forEach {
                Surface(
                    color = Color.Green.copy(alpha = 0.3f),
                    shape = CircleShape
                ) {
                    Text(it.value)
                }
            }
        }
    }
}

@Composable
fun ImageSection(bird: Bird) {
    AsyncImage(
        model = bird.imageUrl,
        placeholder = painterResource(R.drawable.ic_launcher_background),
        contentDescription = bird.name,
        modifier = Modifier.size(100.dp)
    )
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun ExampleScreenPreview() {
    val model = ExampleUiState(
        loading = false,
        birds = listOf(suzume, tsubame, magamo)
    )
    TddKotlinTheme {
        ExampleScreenContent(model = model)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreen() {
    TddKotlinTheme {
        Loading(modifier = Modifier.fillMaxSize())
    }
}
