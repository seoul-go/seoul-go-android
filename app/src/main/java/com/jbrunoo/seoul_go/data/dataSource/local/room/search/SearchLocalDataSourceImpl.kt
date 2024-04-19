package com.jbrunoo.seoul_go.data.dataSource.local.room.search

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchLocalDataSourceImpl @Inject constructor(val searchHistoryDao: SearchHistoryDao) : SearchLocalDataSource {
    override fun fetchSearchHistory(): Flow<List<SearchHistoryEntity>> {
        return searchHistoryDao.getAll()
    }

    override suspend fun insertSearchWord(searchHistoryEntity: SearchHistoryEntity) {
        searchHistoryDao.insert(searchHistoryEntity)
    }

    override suspend fun deleteSearchWord(searchWord: String) {
        searchHistoryDao.delete(searchWord)
    }

    override suspend fun deleteSearchHistory() {
        searchHistoryDao.deleteAll()
    }
}