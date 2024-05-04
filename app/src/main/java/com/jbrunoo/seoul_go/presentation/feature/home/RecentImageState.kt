package com.jbrunoo.seoul_go.presentation.feature.home

import com.jbrunoo.seoul_go.domain.model.RecentEventImage

data class RecentImageState(
    val isLoading: Boolean = false,
    val recentEventImage: List<RecentEventImage> = emptyList(),
    val pagerCount: Int = 0,
    val error: String = ""
)
