package com.jbrunoo.seoul_go.domain.useCase.event

import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// useCase는 interface 등이 아니므로 @Module을 만들 필요 없음
class FetchEventUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(): Flow<List<Event>> = eventRepository.fetchEvents()
}