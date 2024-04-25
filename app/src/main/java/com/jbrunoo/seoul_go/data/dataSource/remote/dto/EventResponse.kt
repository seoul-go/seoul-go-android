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


fun EventResponse.toEvent(): List<Event> {
    return culturalEventInfo.row.map { row ->
        Event(
            CODENAME = row.codeName,
            DATE = row.date,
            END_DATE = row.endDate,
            ETC_DESC = row.etcDesc,
            GUNAME = row.guName,
            IS_FREE = row.isFree,
            LAT = row.lat,
            LOT = row.lot,
            MAIN_IMG = row.mainImg,
            ORG_LINK = row.orgLink,
            ORG_NAME = row.orgName,
            PLACE = row.place,
            PLAYER = row.player,
            PROGRAM = row.program,
            RGSTDATE = row.rgstDate,
            STRTDATE = row.strtDate,
            THEMECODE = row.themeCode,
            TICKET = row.ticket,
            TITLE = row.title,
            USE_FEE = row.useFee,
            USE_TRGT = row.useTrgt,
            IS_FAVORITE = false
        )
    }
}