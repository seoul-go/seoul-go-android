package com.jbrunoo.seoul_go.presentation.feature.event

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.useCase.event.FetchEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class EventViewModel @Inject constructor(
    private val fetchEventUseCase: FetchEventUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _events = MutableStateFlow<List<Event>>(emptyList())
    val events = _events.asStateFlow()

    private val codeName: String = checkNotNull(savedStateHandle["codeName"])

    init {
        fetchEvent(codeName = codeName)
    }

    private fun fetchEvent(codeName: String) {
        fetchEventUseCase(codeName = codeName).onEach { result ->
            when (result) {
                is Resource.Success -> _events.value = result.data ?: emptyList()
                is Resource.Error -> _events.value = emptyList()
                is Resource.Loading -> _events.value = emptyList()
            }
        }.launchIn(viewModelScope)
    }
}