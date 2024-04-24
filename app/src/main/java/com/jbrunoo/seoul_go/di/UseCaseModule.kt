package com.jbrunoo.seoul_go.di

import com.jbrunoo.seoul_go.domain.repository.SearchRepository
import com.jbrunoo.seoul_go.domain.useCase.search.DeleteSearchHistoryUseCase
import com.jbrunoo.seoul_go.domain.useCase.search.DeleteSearchWordUseCase
import com.jbrunoo.seoul_go.domain.useCase.search.FetchSearchHistoryUseCase
import com.jbrunoo.seoul_go.domain.useCase.search.InsertSearchHistoryUseCase
import com.jbrunoo.seoul_go.domain.useCase.search.SearchUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: SearchRepository): SearchUseCases {
        return SearchUseCases(
            fetchSearchHistoryUseCase = FetchSearchHistoryUseCase(repository),
            insertSearchHistoryUseCase = InsertSearchHistoryUseCase(repository),
            deleteSearchWordUseCase = DeleteSearchWordUseCase(repository),
            deleteSearchHistoryUseCase = DeleteSearchHistoryUseCase(repository)
        )
    }
}