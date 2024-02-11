package com.deuk.networkcalldemoapp.network

import com.deuk.networkcalldemoapp.feature.character.CharacterResponse
import retrofit2.http.GET

// Defines network API interface for Retrofit to fetch character data from the Rick and Morty API
interface ApiService {
    @GET("character") // Endpoint to get character list
    suspend fun getCharacters(): CharacterResponse // Asynchronously fetches characters
}