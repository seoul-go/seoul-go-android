package com.jbrunoo.seoul_go.common.constants

import com.jbrunoo.seoul_go.R

enum class EventCategory(
    val codeName: String,
    val categoryName: String,
    val image: Int,
    val label: String
) {
    //    CULTURAL_LESSON_COURSE("문화교양/강좌", "교양/강좌", Icons.Default.Add, "Cultural Lessons/Courses"), // 데이터 0건
    EXHIBITION("전시", "전시/미술", R.drawable.exhibition_icon, "Exhibition/Art"),
    MUSICAL("뮤지컬", "뮤지컬/오페라", R.drawable.musical_icon, "Musical/Opera"),
    THEATER("연극", "연극", R.drawable.theater_icon, "Theater/Drama"),
    Dance("무용", "무용", R.drawable.dance_icon, "Dance"),
    FILM("영화", "영화", R.drawable.film_icon, "Film/Movie"),
    TRADITIONAL_KOREAN_MUSIC(
        "국악",
        "국악",
        R.drawable.traditional_icon,
        "Traditional Korean Music"
    ),
    CONCERT("콘서트", "콘서트", R.drawable.concert_icon, "Concert"),
    CLASSIC_MUSIC("클래식", "클래식", R.drawable.classic_icon, "Classic Music"),
    SOLO_PERFORMANCE_RECITAL(
        "독주",
        "독주/독창회",
        R.drawable.solo_performance_icon,
        "Solo Performance/Recital"
    ),
    FESTIVAL("축제", "축제", R.drawable.festival_icon, "Festival-Culture/Art"),
    OTHERS("기타", "기타", R.drawable.others_icon, "Others"),
}

enum class FestivalDetailCategory(
    val codeName: String,
    val categoryName: String,
    val label: String
) {
    FESTIVAL_CULTURE_ART("축제-문화", "축제-문화/예술", "Festival-Culture/Art"),
    FESTIVAL_TRADITIONAL_HISTORY(
        "축제-전통",
        "축제-전통/역사",
        "Festival-Traditional/History"
    ),
    FESTIVAL_CITIZEN_INTEGRATION(
        "축제-시민",
        "축제-시민화합",
        "Festival-Citizen/Integration"
    ),
    FESTIVAL_LANDSCAPE("축제-자연", "축제-자연/경관", "Festival-Nature/Landscape"),
    FESTIVAL_OTHERS("축제-기타", "축제-기타", "Festival-Others"),
}
