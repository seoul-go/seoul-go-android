package com.jbrunoo.seoul_go.data.dataSource.local

import com.jbrunoo.seoul_go.common.Constants
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveAuth(socialType: Constants.SOCIAL_TYPE)

    fun getAuth(): Flow<String>
}