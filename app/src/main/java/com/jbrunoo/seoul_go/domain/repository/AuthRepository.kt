package com.jbrunoo.seoul_go.domain.repository

import com.jbrunoo.seoul_go.common.Constants
import com.jbrunoo.seoul_go.data.dataSource.local.room.user.UserEntity
import com.jbrunoo.seoul_go.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getAuth(): Flow<String>

    suspend fun setAuth(socialType: Constants.SOCIAL_TYPE)

    fun getUserInfo(userId: String): Flow<User>

    suspend fun setUserInfo(user: User)

    suspend fun deleteUserInfo(userId: String)
}