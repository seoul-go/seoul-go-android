package com.jbrunoo.seoul_go.data.repository

import com.jbrunoo.seoul_go.common.Constants
import com.jbrunoo.seoul_go.data.dataSource.local.AuthLocalDataSource
import com.jbrunoo.seoul_go.data.dataSource.local.room.user.UserEntity
import com.jbrunoo.seoul_go.data.dataSource.local.room.user.UserLocalDataSource
import com.jbrunoo.seoul_go.data.dataSource.mapper.toUser
import com.jbrunoo.seoul_go.data.dataSource.mapper.toUserEntity
import com.jbrunoo.seoul_go.domain.model.User
import com.jbrunoo.seoul_go.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val userLocalDataSource: UserLocalDataSource
): AuthRepository {
    override fun getAuth(): Flow<String> {
        return authLocalDataSource.getAuth()
    }

    override suspend fun setAuth(socialType: Constants.SOCIAL_TYPE) {
        authLocalDataSource.saveAuth(socialType)
    }

    override fun getUserInfo(userId: String): Flow<User> {
        return userLocalDataSource.getUserInfo(userId).map { it.toUser() }
    }

    override suspend fun setUserInfo(user: User) {
        userLocalDataSource.insertUserInfo(user.toUserEntity())
    }

    override suspend fun deleteUserInfo(userId: String) {
        userLocalDataSource.deleteUserInfo(userId)
    }
}