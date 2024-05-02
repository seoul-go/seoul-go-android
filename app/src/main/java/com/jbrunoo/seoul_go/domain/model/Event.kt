package com.jbrunoo.seoul_go.domain.model


data class Event(
    val codeName: String,
    val date: String,
    val endDate: String,
    val etcDesc: String,
    val guName: String,
    val isFree: String, // 무료/유료
    val lat: String?,
    val lot: String?,
    val mainImg: String,
    val orgLink: String,
    val orgName: String,
    val place: String,
    val player: String,
    val program: String,
    val rgstDate: String,
    val strtDate: String,
    val themeCode: String,
    val ticket: String,
    val title: String,
    val useFee: String,
    val useTrgt: String,
    val isFavorite: Boolean
)

data class RecentEventImage(
    val title: String,
    val mainImg: String
)
