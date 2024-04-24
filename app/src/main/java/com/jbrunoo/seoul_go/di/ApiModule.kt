package com.jbrunoo.seoul_go.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jbrunoo.seoul_go.BuildConfig
import com.jbrunoo.seoul_go.data.dataSource.remote.api.EventService
import com.jbrunoo.seoul_go.data.dataSource.remote.api.QueryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
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
    fun provideQueryInterceptor(): QueryInterceptor {
        return QueryInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        queryInterceptor: QueryInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(queryInterceptor)
//            .addInterceptor(queryInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideApiService(networkJson: Json, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): EventService {
        return retrofit.create(EventService::class.java)
    }
}