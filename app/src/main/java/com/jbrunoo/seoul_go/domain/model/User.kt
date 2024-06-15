package com.jbrunoo.seoul_go.domain.model

import android.net.Uri

data class User(
    val id: String,
    val userName: String? = null,
    val userProfileUri: Uri? = null
)