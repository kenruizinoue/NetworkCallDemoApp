package com.deuk.networkcalldemoapp.core

// Enum to represent the various states of data fetching
enum class Status {
    SUCCESS, // Data fetched successfully
    ERROR, // Error occurred during fetching
    LOADING, // Data is currently being loaded
    UPDATING // Data is updating, used for refresh scenarios
}