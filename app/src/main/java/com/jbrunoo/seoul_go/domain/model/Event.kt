package com.jbrunoo.seoul_go.domain.model


data class Event(
    val id: Int,
    val name: String,
    val image: String,
    val isFavorite: Boolean,
)