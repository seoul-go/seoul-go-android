package com.jbrunoo.seoul_go.presentation.feature.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.domain.useCase.event.FetchRecentEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchRecentEventsUseCase: FetchRecentEventsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(RecentImageState())
    val state: StateFlow<RecentImageState> = _state.asStateFlow()

    init {
        fetchRecentEvent()
    }

    private fun fetchRecentEvent() {
        Timber.d("fetchRecentEvent 함수 호출됨")
        fetchRecentEventsUseCase(endIndex = 5).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Timber.d("fetchRecentEvent 11111함수 호출됨")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            recentEventImage = result.data ?: emptyList(),
                            pagerCount = result.data?.size?.times(400) ?: 0
                        )
                    }
                }
                is Resource.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        error = result.message ?: "Unexpected Error"
                    )
                }
                is Resource.Loading -> _state.update {
                    Timber.d("fetchRecentEvent 222222 호출됨")
                    it.copy(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }
}