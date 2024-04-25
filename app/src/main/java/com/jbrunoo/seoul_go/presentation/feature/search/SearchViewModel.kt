package com.jbrunoo.seoul_go.presentation.feature.search

import androidx.lifecycle.ViewModel
import com.jbrunoo.seoul_go.domain.model.SearchHistory
import com.jbrunoo.seoul_go.domain.useCase.search.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCases: SearchUseCases): ViewModel() {

}