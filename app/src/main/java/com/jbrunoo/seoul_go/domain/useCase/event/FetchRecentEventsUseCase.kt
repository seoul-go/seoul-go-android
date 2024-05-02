package com.jbrunoo.seoul_go.domain.useCase.event

import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.model.RecentEventImage
import com.jbrunoo.seoul_go.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchRecentEventsUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(endIndex: Int): Flow<Resource<List<RecentEventImage>>> = eventRepository.fetchRecentEventsFlow(endIndex = endIndex)
}