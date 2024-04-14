package com.jbrunoo.seoul_go.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jbrunoo.seoul_go.data.dataSource.remote.api.EventService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideApiService(networkJson: Json): EventService =
        Retrofit.Builder()
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://0ea3e3a7-4f20-4a63-88d7-24b209df42e9.mock.pstmn.io")
            .build()
            .create(EventService::class.java)
}