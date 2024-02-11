package com.deuk.networkcalldemoapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deuk.networkcalldemoapp.core.DataState
import com.deuk.networkcalldemoapp.core.Status
import com.deuk.networkcalldemoapp.feature.character.ToonCharacter

// Composable function to display a refreshable list of characters
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefreshableList(
    dataState: DataState<List<ToonCharacter>>,
    isRefreshing: Boolean,
    pullRefreshState: PullRefreshState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    backgroundColorWhileUpdate: Color = Color.LightGray,
    contentModifier: Modifier = Modifier.fillMaxSize(),
    errorContent: @Composable ((String?) -> Unit)? = null, // Custom error content
    loadingContent: @Composable (() -> Unit)? = null // Custom loading content
) {
    // Box layout to handle pull to refresh and display content based on data state
    Box(
        modifier = modifier
            .background(if (dataState.status == Status.UPDATING) backgroundColorWhileUpdate else backgroundColor)
            .fillMaxSize()
            .pullRefresh(state = pullRefreshState)
    ) {
        when (dataState.status) {
            Status.SUCCESS, Status.UPDATING -> {
                LazyColumn(modifier = contentModifier) {
                    dataState.data?.let { characters ->
                        items(characters) { character ->
                            CharacterItem(character)
                        }
                    }
                }
            }
            Status.ERROR -> errorContent?.invoke(dataState.message)
                ?: DefaultErrorContent(
                    dataState.message, Modifier.align(Alignment.Center)
                )
            Status.LOADING -> loadingContent?.invoke() ?: DefaultLoadingContent(
                Modifier.align(Alignment.Center)
            )
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

// Default composable functions for loading and error states for modular error handling and UI updates
@Composable
fun DefaultErrorContent(message: String?, modifier: Modifier = Modifier) {
    Text(
        text = message ?: "Error",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun DefaultLoadingContent(modifier: Modifier = Modifier) {
    Text(
        text = "Loading...",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun CharacterItem(toonCharacter: ToonCharacter) {
    Text(
        text = toonCharacter.name,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}