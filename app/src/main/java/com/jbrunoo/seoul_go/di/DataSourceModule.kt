package com.jbrunoo.seoul_go.di

import com.jbrunoo.seoul_go.data.dataSource.remote.EventRemoteDataSource
import com.jbrunoo.seoul_go.data.dataSource.remote.EventRemoteDataSourceImpl
import com.jbrunoo.seoul_go.data.dataSource.remote.api.EventService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(eventRemoteDataSourceImpl: EventRemoteDataSourceImpl): EventRemoteDataSource

}