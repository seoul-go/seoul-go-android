package com.jbrunoo.seoul_go.data.dataSource.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CulturalEventInfo(
    @SerialName("list_total_count")
    val listTotalCount: Int,
    @SerialName("RESULT")
    val result: RESULT,
    @SerialName("row")
    val row: List<Row>
)