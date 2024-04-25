package com.jbrunoo.seoul_go.data.dataSource.remote

import com.jbrunoo.seoul_go.data.dataSource.remote.dto.EventResponse
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {
    suspend fun fetchEvents() : Flow<EventResponse>
}