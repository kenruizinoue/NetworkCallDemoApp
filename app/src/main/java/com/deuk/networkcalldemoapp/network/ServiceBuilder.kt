package com.deuk.networkcalldemoapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Builds and provides Retrofit instance for network requests
object ServiceBuilder {

    private const val BASE_URL = "https://rickandmortyapi.com/api/" // Base URL of the API

    // Configures and returns a Retrofit instance with Gson converter
    private fun getRetrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Parses JSON to Kotlin objects
            .build()
    }

    // Lazy initialization of ApiService to avoid unnecessary instantiation
    val apiService: ApiService = getRetrofitService().create(ApiService::class.java)
}