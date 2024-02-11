package com.deuk.networkcalldemoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import com.deuk.networkcalldemoapp.feature.character.CharacterViewModel
import com.deuk.networkcalldemoapp.core.Status
import com.deuk.networkcalldemoapp.ui.composable.RefreshableList
import dagger.hilt.android.AndroidEntryPoint

// Entry point of the app's main screen, setups up UI and data state management
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // ViewModel setup with Hilt for dependency injection
    private val characterViewModel: CharacterViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dataState by characterViewModel.characters.collectAsState()
            // Directly infer the refreshing state from the resource status.
            val isRefreshing =
                dataState.status == Status.LOADING || dataState.status == Status.UPDATING
            val pullRefreshState = rememberPullRefreshState(isRefreshing, characterViewModel::fetchCharacters)
            RefreshableList(
                dataState = dataState,
                isRefreshing = isRefreshing,
                pullRefreshState = pullRefreshState
            )
        }
    }
}