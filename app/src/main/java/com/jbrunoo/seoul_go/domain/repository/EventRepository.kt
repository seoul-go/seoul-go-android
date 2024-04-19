package com.jbrunoo.seoul_go.domain.repository

import com.jbrunoo.seoul_go.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun fetchEvents(): Flow<List<Event>>
}