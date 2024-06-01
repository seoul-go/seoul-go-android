package com.jbrunoo.seoul_go.domain.useCase.event

import com.jbrunoo.seoul_go.common.EventOrder
import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// useCase는 interface 등이 아니므로 @Module을 만들 필요 없음
class FetchEventsUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(
        codeName: String = " ",
        title: String = " ",
        eventOrder: EventOrder = EventOrder.ByRegistration
    ): Flow<Resource<List<Event>>> =
        eventRepository.fetchEventsByCodeFlow(codeName = codeName, title = title).map { eventResource ->
            when(eventOrder) {
                is EventOrder.ByRegistration -> eventResource
                is EventOrder.ByDeadline -> {
                    when(eventResource) {
                        is Resource.Success -> {
                            val eventsByDeadline = eventResource.data?.sortedBy { it.endDate } ?: emptyList()
                            Resource.Success(eventsByDeadline)
                        }
                        is Resource.Loading -> eventResource
                        is Resource.Error -> eventResource
                    }
                }
                is EventOrder.ByCost -> {
                    when(eventResource) {
                        is Resource.Success -> {
                            val eventsByCost = eventResource.data?.sortedBy { it.isFree } ?: emptyList()
                            Resource.Success(eventsByCost)
                        }
                        is Resource.Loading -> eventResource
                        is Resource.Error -> eventResource
                    }
                }
            }
        }
}