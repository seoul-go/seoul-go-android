package com.jbrunoo.seoul_go.presentation.feature.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.useCase.event.FetchRecentEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchRecentEventsUseCase: FetchRecentEventsUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(RecentImageState())
    val state: State<RecentImageState> = _state

    init {
        fetchRecentEvent()
    }

    private fun fetchRecentEvent() {
        fetchRecentEventsUseCase(endIndex = 5).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RecentImageState(
                        recentEventImage = result.data ?: emptyList(),
                        pagerCount = result.data?.size?.times(400) ?: 0
                    )
                }
                is Resource.Error -> _state.value = RecentImageState(error = result.message ?: "Unexpected Error")
                is Resource.Loading -> _state.value = RecentImageState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}