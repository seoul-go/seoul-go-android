package com.jbrunoo.seoul_go.data.dataSource.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RESULT(
    @SerialName("CODE")
    val code: String,
    @SerialName("MESSAGE")
    val message: String
)