package com.jbrunoo.seoul_go.data.repository

import com.jbrunoo.seoul_go.data.dataSource.local.room.search.SearchLocalDataSource
import com.jbrunoo.seoul_go.data.dataSource.mapper.toSearchHistoryList
import com.jbrunoo.seoul_go.data.dataSource.mapper.toSearchHistoryEntity
import com.jbrunoo.seoul_go.domain.model.SearchHistory
import com.jbrunoo.seoul_go.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchLocalDataSource: SearchLocalDataSource): SearchRepository  {
    override fun fetchSearchHistory(): Flow<List<SearchHistory>> {
        return searchLocalDataSource.fetchSearchHistory().map { it.toSearchHistoryList() }
    }

    override suspend fun insertSearchWord(searchHistory: SearchHistory) {
        searchLocalDataSource.insertSearchWord(searchHistory.toSearchHistoryEntity())
    }

    override suspend fun deleteSearchWord(searchWord: String) {
        searchLocalDataSource.deleteSearchWord(searchWord)
    }

    override suspend fun deleteSearchHistory() {
        searchLocalDataSource.deleteSearchHistory()
    }
}