package com.jbrunoo.seoul_go.presentation.feature.event_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.useCase.event.FetchEventsUseCase
import com.jbrunoo.seoul_go.utils.removeBracketAndNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val fetchEventUseCase: FetchEventsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _event = MutableStateFlow<List<Event>>(emptyList())
    val event = _event.asStateFlow()

    private val title: String = checkNotNull(savedStateHandle["title"])

    init {
        Timber.d("기존 행사명 $title")
        val regexTitle = title.removeBracketAndNumber()
        Timber.d("졍규식 행사명 $regexTitle")
        fetchEvent(title = regexTitle)
    }

    private fun fetchEvent(title: String) {
        fetchEventUseCase(title = title).onEach { result ->
            when (result) {
                is Resource.Success -> _event.value = result.data ?: emptyList()
                is Resource.Error -> _event.value = emptyList()
                is Resource.Loading -> _event.value = emptyList()
            }
        }.launchIn(viewModelScope)
    }
}