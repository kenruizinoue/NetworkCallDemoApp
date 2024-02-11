package com.deuk.networkcalldemoapp.feature.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deuk.networkcalldemoapp.core.DataState
import com.deuk.networkcalldemoapp.core.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel to manage UI-related data and handle business logic for character fetching
@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    // StateFlow mechanism to observe characters data state from the UI layer
    private val _characters: MutableStateFlow<DataState<List<ToonCharacter>>> =
        MutableStateFlow(DataState.loading(data = null))
    val characters = _characters.asStateFlow()

    init {
        fetchCharacters() // Initial data fetch
    }

    // Handles fetching characters from the repository and updating UI state accordingly
    fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_characters.value.status == Status.SUCCESS) {
                // There is already data, update states consist of fetching while showing the old data
                // Use copy for updating the status
                _characters.value = _characters.value.copy(status = Status.UPDATING)
                // Freezes 1 second in case of updating state
                delay(1000)
            }
            characterRepository.getCharacters().collect {
                _characters.value = it
            }
        }
    }
}