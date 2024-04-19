package com.jbrunoo.seoul_go.data.dataSource.mapper

import com.jbrunoo.seoul_go.data.dataSource.local.room.search.SearchHistoryEntity
import com.jbrunoo.seoul_go.domain.model.SearchHistory

fun List<SearchHistoryEntity>.toSearchHistoryList(): List<SearchHistory> {
    return this.map { searchHistoryEntity ->
           SearchHistory(
               searchWord = searchHistoryEntity.searchWord
           )
    }
}

fun SearchHistory.toSearchHistoryEntity(): SearchHistoryEntity {
    return SearchHistoryEntity(
        searchWord = searchWord
    )
}