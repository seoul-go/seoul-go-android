package com.jbrunoo.seoul_go.domain.model


data class Event(
    val CODENAME: String,
    val DATE: String,
    val END_DATE: String,
    val ETC_DESC: String,
    val GUNAME: String,
    val IS_FREE: String,
    val LAT: String?,
    val LOT: String?,
    val MAIN_IMG: String,
    val ORG_LINK: String,
    val ORG_NAME: String,
    val PLACE: String,
    val PLAYER: String,
    val PROGRAM: String,
    val RGSTDATE: String,
    val STRTDATE: String,
    val THEMECODE: String,
    val TICKET: String,
    val TITLE: String,
    val USE_FEE: String,
    val USE_TRGT: String,
    val IS_FAVORITE: Boolean
)