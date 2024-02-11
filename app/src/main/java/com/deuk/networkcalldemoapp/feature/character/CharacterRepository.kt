package com.deuk.networkcalldemoapp.feature.character

import com.deuk.networkcalldemoapp.network.ApiService
import com.deuk.networkcalldemoapp.core.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

// Repository class to abstract the fetching of character data through ApiService
class CharacterRepository(private val apiService: ApiService) {

    // Fetches characters from the API and emits the state accordingly
    fun getCharacters(): Flow<DataState<List<ToonCharacter>>> =
        // Flow builder to handle data fetching asynchronously with proper state management
        flow {
            val response = apiService.getCharacters()
            emit(DataState.success(data = response.results))
        }
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(
                    DataState.error(
                        message = e.message ?: "An error occurred while fetching characters",
                        error = e
                    )
                )
            }
}