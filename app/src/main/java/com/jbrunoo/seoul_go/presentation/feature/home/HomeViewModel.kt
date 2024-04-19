package com.jbrunoo.seoul_go.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.useCase.event.FetchEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getEventUseCase: FetchEventUseCase): ViewModel() {
    private var _events = MutableStateFlow<List<Event>>(emptyList())
    val events = _events.asStateFlow()

    init {
        viewModelScope.launch {
            getEventUseCase().collect {
                _events.value = it
            }
        }
    }
}