package com.jbrunoo.seoul_go.utils

import timber.log.Timber
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getEventStatus(strtDate: String, endDate: String): String {
    if (strtDate.isBlank() || endDate.isBlank()) return "정보없음"

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")
    // 시작, 마감 시간이 둘 다 00:00:00.0 이기 때문에 날짜 부분만 사용
    val strt = LocalDateTime.parse(strtDate, formatter).toLocalDate()
    val end = LocalDateTime.parse(endDate, formatter).toLocalDate()
    val current = LocalDate.now()

    Timber.d("현재 시간 : $current")
    return when {
        current.isBefore(strt) -> "공연예정"
        current.isAfter(end) -> "공연마감"
        else -> "공연중"
    }
}