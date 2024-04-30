package com.jbrunoo.seoul_go.data.dataSource.remote

import com.jbrunoo.seoul_go.data.dataSource.remote.api.EventService
import com.jbrunoo.seoul_go.data.dataSource.remote.dto.EventResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRemoteDataSourceImpl @Inject constructor(private val eventService: EventService) :
    EventRemoteDataSource {
    override fun fetchEventsByCodeNameFlow(codeName: String): Flow<EventResponse> {
        return flow {
            emit(eventService.getEvents(codeName = codeName))
        }
    }

    override fun searchEventsFlow(codeName: String, title: String): Flow<EventResponse> {
        return flow {
            emit(eventService.getEvents(codeName = codeName, title = title))
        }
    }
}