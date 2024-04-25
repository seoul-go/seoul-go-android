package com.jbrunoo.seoul_go.data.dataSource.local.room.search

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM search ORDER BY create_at")
    fun getAll(): Flow<List<SearchHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchWordEntity: SearchHistoryEntity)

    @Query("DELETE FROM search WHERE searchWord = :searchWord")
    suspend fun delete(searchWord: String)

    @Query("DELETE FROM search")
    suspend fun deleteAll()
}