package com.jbrunoo.seoul_go.di

import com.jbrunoo.seoul_go.data.repository.EventRepositoryImpl
import com.jbrunoo.seoul_go.data.repository.SearchRepositoryImpl
import com.jbrunoo.seoul_go.domain.repository.EventRepository
import com.jbrunoo.seoul_go.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideEventRepo(eventRepositoryImpl: EventRepositoryImpl): EventRepository

    @Binds
    @Singleton
    abstract fun provideSearchRepo(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}