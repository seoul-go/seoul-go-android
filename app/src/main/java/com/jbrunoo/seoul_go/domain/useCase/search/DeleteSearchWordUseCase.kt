package com.jbrunoo.seoul_go.domain.useCase.search

import com.jbrunoo.seoul_go.domain.repository.SearchRepository
import javax.inject.Inject

class DeleteSearchWordUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(searchWord: String) = searchRepository.deleteSearchWord(searchWord)
}