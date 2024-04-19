package com.jbrunoo.seoul_go.domain.repository

import com.jbrunoo.seoul_go.data.dataSource.local.room.search.SearchHistoryEntity
import com.jbrunoo.seoul_go.domain.model.SearchHistory
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun fetchSearchHistory(): Flow<List<SearchHistory>>

    suspend fun insertSearchWord(searchHistory: SearchHistory)

    suspend fun deleteSearchWord(searchWord: String)

    suspend fun deleteSearchHistory()
}