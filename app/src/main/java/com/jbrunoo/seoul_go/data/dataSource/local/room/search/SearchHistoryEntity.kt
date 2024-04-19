package com.jbrunoo.seoul_go.data.dataSource.local.room.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jbrunoo.seoul_go.domain.model.SearchHistory

@Entity(tableName = "search")
data class SearchHistoryEntity(
    @PrimaryKey val searchWord: String,
    @ColumnInfo(name = "create_at") val createAt: Long = System.currentTimeMillis()
)