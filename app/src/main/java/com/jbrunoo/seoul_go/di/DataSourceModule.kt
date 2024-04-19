package com.jbrunoo.seoul_go.di

import com.jbrunoo.seoul_go.data.dataSource.local.room.event.EventLocalDataSource
import com.jbrunoo.seoul_go.data.dataSource.local.room.event.EventLocalDataSourceImpl
import com.jbrunoo.seoul_go.data.dataSource.local.room.search.SearchLocalDataSource
import com.jbrunoo.seoul_go.data.dataSource.local.room.search.SearchLocalDataSourceImpl
import com.jbrunoo.seoul_go.data.dataSource.remote.EventRemoteDataSource
import com.jbrunoo.seoul_go.data.dataSource.remote.EventRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideEventLocalDataSource(eventLocalDataSourceImpl: EventLocalDataSourceImpl): EventLocalDataSource


    @Binds
    @Singleton
    abstract fun provideEventRemoteDataSource(eventRemoteDataSourceImpl: EventRemoteDataSourceImpl): EventRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideSearchLocalDataSource(searchLocalDataSourceImpl: SearchLocalDataSourceImpl): SearchLocalDataSource
}