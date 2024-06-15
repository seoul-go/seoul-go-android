package com.jbrunoo.seoul_go.data.dataSource.local.room.user

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    fun getUserInfo(userId: String): Flow<UserEntity>

    suspend fun insertUserInfo(user: UserEntity)

    suspend fun deleteUserInfo(userId: String)
}