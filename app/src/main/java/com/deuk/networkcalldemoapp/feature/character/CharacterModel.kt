package com.deuk.networkcalldemoapp.feature.character

// Response model for character API call, containing list of characters and pagination info
data class CharacterResponse(
    val info: Info, // Pagination and metadata information
    val results: List<ToonCharacter> // List of character details
)

// Defines a character with their attributes as received from the API
data class ToonCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

// Model for pagination information in API response
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

// Represents origin location of a character
data class Origin(
    val name: String,
    val url: String
)

// Represents current location of a character
data class Location(
    val name: String,
    val url: String
)