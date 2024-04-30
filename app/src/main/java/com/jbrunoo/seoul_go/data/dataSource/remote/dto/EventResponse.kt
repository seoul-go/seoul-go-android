package com.jbrunoo.seoul_go.data.dataSource.remote.dto


import com.jbrunoo.seoul_go.domain.model.Event
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class EventResponse(
    @SerialName("culturalEventInfo")
    val culturalEventInfo: CulturalEventInfo
)