package com.jbrunoo.seoul_go.data.repository

import com.jbrunoo.seoul_go.data.dataSource.remote.EventRemoteDataSource
import com.jbrunoo.seoul_go.data.dataSource.remote.dto.toEvent
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(private val eventRemoteDataSource: EventRemoteDataSource) :
    EventRepository {
    override fun fetchEvents(): Flow<List<Event>> =
        eventRemoteDataSource.fetchEvents().map { it.map { it.toEvent() } }
}

