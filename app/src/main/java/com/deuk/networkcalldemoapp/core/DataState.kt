package com.deuk.networkcalldemoapp.core

// Wrapper class for managing data state and UI state in response to network requests
data class DataState<out T>(val status: Status, val data: T?, val message: String?, val error: Throwable? = null) {
    // Factory methods for different states: success, error, loading
    companion object {

        // Successful network request, with data
        fun <T> success(data: T): DataState<T> = DataState(Status.SUCCESS, data, null)

        // Network error, no data
        fun <T> error(message: String, error: Throwable, data: T? = null): DataState<T> = DataState(
            Status.ERROR, data, message, error)

        // No previous data, empty state
        fun <T> loading(data: T? = null): DataState<T> = DataState(Status.LOADING, data, null)
    }
}
