package com.jbrunoo.seoul_go.data.dataSource.remote

import com.jbrunoo.seoul_go.data.dataSource.remote.dto.EventResponse
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {
    fun getEvents() : Flow<List<EventResponse>>
}