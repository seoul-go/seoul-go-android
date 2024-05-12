package com.jbrunoo.seoul_go.utils

import timber.log.Timber
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getEventStatus(strtDate: String, endDate: String): EventStatus {
    if (strtDate.isBlank() || endDate.isBlank()) return EventStatus.UNKNOWN

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")
    // 시작, 마감 시간이 둘 다 00:00:00.0 이기 때문에 날짜 부분만 사용
    val strt = LocalDateTime.parse(strtDate, formatter).toLocalDate()
    val end = LocalDateTime.parse(endDate, formatter).toLocalDate()
    val current = LocalDate.now()

    Timber.d("현재 시간 : $current")
    return when {
        current.isBefore(strt) -> EventStatus.UPCOMING
        current.isAfter(end) -> EventStatus.CLOSED
        else -> EventStatus.ONGOING
    }
}

enum class EventStatus(val status: String) {
    ONGOING("공연 중"),
    UPCOMING("공연 예정"),
    CLOSED("공연 마감"),
    UNKNOWN("정보 없음")
}