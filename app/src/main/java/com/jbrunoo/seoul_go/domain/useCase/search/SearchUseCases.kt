package com.jbrunoo.seoul_go.domain.useCase.search

data class SearchUseCases(
    val fetchSearchHistoryUseCase: FetchSearchHistoryUseCase,
    val insertSearchHistoryUseCase: InsertSearchHistoryUseCase,
    val deleteSearchWordUseCase: DeleteSearchWordUseCase,
    val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase
)
