package com.jbrunoo.seoul_go.common

object Constants {
    const val AUTH_DATASTORE = "auth_dataStore"

    enum class SOCIAL_TYPE(val type: String) {
        GOOGLE("google"),
        KAKAO("kakao"),
        GUEST("guest"),
        UNAUTHENTICATED("unauthenticated")
    }
}