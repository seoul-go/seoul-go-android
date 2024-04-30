package com.jbrunoo.seoul_go.data.dataSource.remote.dto


import com.jbrunoo.seoul_go.domain.model.Event
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Row(
    @SerialName("CODENAME")
    val codeName: String,
    @SerialName("DATE")
    val date: String,
    @SerialName("END_DATE")
    val endDate: String,
    @SerialName("ETC_DESC")
    val etcDesc: String,
    @SerialName("GUNAME")
    val guName: String,
    @SerialName("HMPG_ADDR")
    val hmpgAddr: String,
    @SerialName("IS_FREE")
    val isFree: String,
    @SerialName("LAT")
    val lat: String,
    @SerialName("LOT")
    val lot: String,
    @SerialName("MAIN_IMG")
    val mainImg: String,
    @SerialName("ORG_LINK")
    val orgLink: String,
    @SerialName("ORG_NAME")
    val orgName: String,
    @SerialName("PLACE")
    val place: String,
    @SerialName("PLAYER")
    val player: String,
    @SerialName("PROGRAM")
    val program: String,
    @SerialName("RGSTDATE")
    val rgstDate: String,
    @SerialName("STRTDATE")
    val strtDate: String,
    @SerialName("THEMECODE")
    val themeCode: String,
    @SerialName("TICKET")
    val ticket: String,
    @SerialName("TITLE")
    val title: String,
    @SerialName("USE_FEE")
    val useFee: String,
    @SerialName("USE_TRGT")
    val useTrgt: String
)

fun Row.toEvent(): Event {
    return Event(
        CODENAME = codeName,
        DATE = date,
        END_DATE = endDate,
        ETC_DESC = etcDesc,
        GUNAME = guName,
        IS_FREE = isFree,
        LAT = lat,
        LOT = lot,
        MAIN_IMG = mainImg,
        ORG_LINK = orgLink,
        ORG_NAME = orgName,
        PLACE = place,
        PLAYER = player,
        PROGRAM = program,
        RGSTDATE = rgstDate,
        STRTDATE = strtDate,
        THEMECODE = themeCode,
        TICKET = ticket,
        TITLE = title,
        USE_FEE = useFee,
        USE_TRGT = useTrgt,
        IS_FAVORITE = false
    )
}
