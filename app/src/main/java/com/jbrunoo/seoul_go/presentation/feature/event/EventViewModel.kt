package com.jbrunoo.seoul_go.presentation.feature.event

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbrunoo.seoul_go.common.EventOrder
import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.useCase.event.FetchEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class EventViewModel @Inject constructor(
    private val fetchEventUseCase: FetchEventsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _events = MutableStateFlow<List<Event>>(emptyList())
    val events = _events.asStateFlow()

    val codeName: String = checkNotNull(savedStateHandle["codeName"]) // 기본적으로 nullable type

    private var _eventOrder = MutableStateFlow<EventOrder>(EventOrder.ByRegistration)
    private val eventOrder = _eventOrder.asStateFlow()

    init {
        fetchEvent(codeName = codeName, eventOrder.value)
    }

    fun sortEvent(eventOrder: EventOrder) {
        fetchEvent(codeName = codeName, eventOrder = eventOrder)
    }

    private fun fetchEvent(codeName: String, eventOrder: EventOrder = EventOrder.ByRegistration) {
        fetchEventUseCase(codeName = codeName, eventOrder = eventOrder).onEach { result ->
            when (result) {
                is Resource.Success -> _events.value = result.data ?: emptyList()
                is Resource.Error -> _events.value = emptyList()
                is Resource.Loading -> _events.value = emptyList()
            }
        }.launchIn(viewModelScope)
    }
}