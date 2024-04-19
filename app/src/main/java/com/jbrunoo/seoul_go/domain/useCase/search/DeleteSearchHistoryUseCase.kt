package com.jbrunoo.seoul_go.domain.useCase.search

import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.model.SearchHistory
import com.jbrunoo.seoul_go.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteSearchHistoryUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend operator fun invoke() = searchRepository.deleteSearchHistory()
}