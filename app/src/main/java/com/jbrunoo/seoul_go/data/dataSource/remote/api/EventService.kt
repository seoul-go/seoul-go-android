package com.jbrunoo.seoul_go.data.dataSource.remote.api

import com.jbrunoo.seoul_go.data.dataSource.remote.dto.EventResponse
import retrofit2.http.GET

interface EventService {
    @GET("test/event")
    suspend fun getEvents(): List<EventResponse>
}