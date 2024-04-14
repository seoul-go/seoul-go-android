package com.jbrunoo.seoul_go.domain.repository

import com.jbrunoo.seoul_go.domain.entity.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEvents(): Flow<List<Event>>
}