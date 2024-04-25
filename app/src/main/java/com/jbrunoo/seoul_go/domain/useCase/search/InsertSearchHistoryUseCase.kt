package com.jbrunoo.seoul_go.domain.useCase.search

import com.jbrunoo.seoul_go.domain.model.SearchHistory
import com.jbrunoo.seoul_go.domain.repository.SearchRepository
import javax.inject.Inject

class InsertSearchHistoryUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(searchHistory: SearchHistory) = searchRepository.insertSearchWord(searchHistory)
}