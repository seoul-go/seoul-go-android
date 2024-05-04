package com.jbrunoo.seoul_go.domain.repository

import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.model.RecentEventImage
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun fetchEventsByCodeFlow(codeName: String, title: String = " ") : Flow<Resource<List<Event>>>

    fun fetchRecentEventsFlow(endIndex: Int) : Flow<Resource<List<RecentEventImage>>>
}