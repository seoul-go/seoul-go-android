package com.jbrunoo.seoul_go.domain.repository

import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun fetchEventsByCodeFlow(codeName: String) : Flow<Resource<List<Event>>>
    fun searchEventsFlow(codeName: String = " ", title: String) : Flow<Resource<List<Event>>>
}