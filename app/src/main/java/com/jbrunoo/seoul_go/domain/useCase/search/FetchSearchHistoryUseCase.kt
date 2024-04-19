package com.jbrunoo.seoul_go.domain.useCase.search

import com.jbrunoo.seoul_go.domain.model.SearchHistory
import com.jbrunoo.seoul_go.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSearchHistoryUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    operator fun invoke(): Flow<List<SearchHistory>> = searchRepository.fetchSearchHistory()

}