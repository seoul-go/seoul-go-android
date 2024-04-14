package com.jbrunoo.seoul_go.domain.useCase

import com.jbrunoo.seoul_go.domain.entity.Event
import com.jbrunoo.seoul_go.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// useCase는 interface 등이 아니므로 @Module을 만들 필요 없음
class GetEventUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(): Flow<List<Event>> = eventRepository.getEvents()
}