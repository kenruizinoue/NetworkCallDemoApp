package com.deuk.networkcalldemoapp.di

import com.deuk.networkcalldemoapp.feature.character.CharacterRepository
import com.deuk.networkcalldemoapp.network.ApiService
import com.deuk.networkcalldemoapp.network.ServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object MainModule {
    // Declares a Dagger module to encapsulate the RepositoryModule.
    @Module
    // Specifies that the lifetime of dependencies provided by this module will be the same as the application's.
    @InstallIn(SingletonComponent::class)
    object DataModule {

        // Provides a singleton instance of ApiService to be used across the application.
        @Provides
        @Singleton
        fun provideApiService(): ApiService {
            return ServiceBuilder.apiService
        }

        // Provides a singleton instance of CharacterRepository. It takes an ApiService instance as a parameter.
        @Provides
        @Singleton
        fun provideCharacterRepository(apiService: ApiService): CharacterRepository {
            return CharacterRepository(apiService)
        }
    }
}