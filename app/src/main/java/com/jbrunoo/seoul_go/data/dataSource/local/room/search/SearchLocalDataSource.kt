package com.jbrunoo.seoul_go.data.dataSource.local.room.search

import kotlinx.coroutines.flow.Flow

interface SearchLocalDataSource {
    fun fetchSearchHistory(): Flow<List<SearchHistoryEntity>>

    suspend fun insertSearchWord(searchHistoryEntity: SearchHistoryEntity)

    suspend fun deleteSearchWord(searchWord: String)

    suspend fun deleteSearchHistory()

}