package com.jbrunoo.seoul_go.data.dataSource.remote.dto

import com.jbrunoo.seoul_go.domain.model.Event
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventResponse(
    @SerialName("event_id") val id: Int,
    @SerialName("event_name") val name: String,
    @SerialName("event_image") val image: String,
    @SerialName("isFavorite") val isFavorite: Boolean,
)

fun EventResponse.toEvent(): Event {
    return Event(
        id = id,
        name = name,
        image = image,
        isFavorite = isFavorite
    )
}