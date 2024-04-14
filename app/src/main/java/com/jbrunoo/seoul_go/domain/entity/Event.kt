package com.jbrunoo.seoul_go.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.lang.invoke.TypeDescriptor

data class Event(
    val id: Int,
    val name: String,
    val image: String,
    val isFavorite: Boolean,
)