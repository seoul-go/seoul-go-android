package com.jbrunoo.seoul_go.data.dataSource.remote

import com.jbrunoo.seoul_go.data.dataSource.remote.dto.EventResponse
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {
    fun fetchEventsByCodeNameFlow(codeName: String) : Flow<EventResponse>


//    suspend fun fetchRecentFiveEvents(endIndex: Int = 5, codeName: String) : Flow<EventResponse>
    fun searchEventsFlow(codeName: String, title: String) : Flow<EventResponse>

}