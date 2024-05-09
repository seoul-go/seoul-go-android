package com.jbrunoo.seoul_go.utils

fun String.removeBracketAndNumber(): String {
    // 확인된 오류는 대괄호와 숫자, 연속 공백 있으면 오류 // api 문제라 이렇게라도..
    val regex = Regex("""(?:\[[^\]]*\]|\b\d+\b)\s*""")
    var result = this.replace(regex, "")
    return result
}