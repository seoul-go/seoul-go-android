package com.jbrunoo.seoul_go.domain.useCase.event

import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// useCase는 interface 등이 아니므로 @Module을 만들 필요 없음
class FetchEventsUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(codeName: String, title: String = " "): Flow<Resource<List<Event>>> = eventRepository.fetchEventsByCodeFlow(codeName = codeName, title = title)
}