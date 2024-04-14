package com.jbrunoo.seoul_go.data.dataSource.remote

import com.jbrunoo.seoul_go.data.dataSource.remote.api.EventService
import com.jbrunoo.seoul_go.data.dataSource.remote.dto.EventResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRemoteDataSourceImpl @Inject constructor(private val eventService: EventService) :
    EventRemoteDataSource {
    override fun getEvents(): Flow<List<EventResponse>> {
        return flow {
            emit(eventService.getEvents())
        }
    }
}