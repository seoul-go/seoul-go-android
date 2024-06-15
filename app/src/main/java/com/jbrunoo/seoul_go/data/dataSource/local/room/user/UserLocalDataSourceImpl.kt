package com.jbrunoo.seoul_go.data.dataSource.local.room.user

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val userDao: UserDao): UserLocalDataSource {
    override fun getUserInfo(userId: String): Flow<UserEntity> {
        return userDao.getUser(userId)
    }

    override suspend fun insertUserInfo(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun deleteUserInfo(userId: String) {
        userDao.deleteUser(userId)
    }
}