package com.jbrunoo.seoul_go.di

import com.jbrunoo.seoul_go.data.repository.EventRepositoryImpl
import com.jbrunoo.seoul_go.domain.repository.EventRepository
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
    abstract fun provideRepository(eventRepositoryImpl: EventRepositoryImpl): EventRepository

}