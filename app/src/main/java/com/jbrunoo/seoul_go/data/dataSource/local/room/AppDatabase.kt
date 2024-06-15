package com.jbrunoo.seoul_go.data.dataSource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jbrunoo.seoul_go.data.dataSource.local.room.search.SearchHistoryDao
import com.jbrunoo.seoul_go.data.dataSource.local.room.search.SearchHistoryEntity
import com.jbrunoo.seoul_go.data.dataSource.local.room.user.UserDao
import com.jbrunoo.seoul_go.data.dataSource.local.room.user.UserEntity

@Database(entities = [SearchHistoryEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun userDao(): UserDao
}