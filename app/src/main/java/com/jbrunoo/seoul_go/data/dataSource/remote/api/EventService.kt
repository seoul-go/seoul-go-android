package com.jbrunoo.seoul_go.data.dataSource.remote.api

import com.jbrunoo.seoul_go.data.dataSource.remote.dto.EventResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {
    companion object {
        const val prefixPath = "KEY/TYPE/SERVICE/START_INDEX/"
    }
    @GET("${prefixPath}{END_INDEX}/{CODENAME}/{TITLE}/{DATE}")
    suspend fun getEvents(
        @Path("END_INDEX") endIndex: Int = 1000,
        @Path("CODENAME") codeName: String = " ",
        @Path("TITLE") title: String = " ",
        @Path("DATE") date: String = " "
    ): EventResponse
}