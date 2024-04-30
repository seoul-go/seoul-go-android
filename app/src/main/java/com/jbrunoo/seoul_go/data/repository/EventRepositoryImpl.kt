package com.jbrunoo.seoul_go.data.repository

import android.util.Log
import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.data.dataSource.remote.EventRemoteDataSource
import com.jbrunoo.seoul_go.data.dataSource.remote.dto.toEvent
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.repository.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(private val eventRemoteDataSource: EventRemoteDataSource) :
    EventRepository {
    override fun fetchEventsByCodeFlow(codeName: String): Flow<Resource<List<Event>>> {
        return eventRemoteDataSource.fetchEventsByCodeNameFlow(codeName = codeName).map { eventResponse ->
            val result = eventResponse.culturalEventInfo.result
            val row = eventResponse.culturalEventInfo.row

            try {
                when (result.code) {
                    "INFO-000" -> {
                        Timber.d("SUCCESS")
                        val events = row.map { it.toEvent() }
                        Resource.Success(events)
                    }
                    "INFO-200" -> {
                        Timber.d("INFO-200")
                        Resource.Error(message = result.message)
                    }
                    "ERROR-500", "ERROR-600", "ERROR-601" -> {
                        Timber.d("SERVER-ERROR")
                        Resource.Error(message = "서버 데이터 점검 중 입니다.")
                    }
                    else -> {
                        Timber.d("OTHER ERRORS")
                        Resource.Error(message = "데이터 요청에 실패하였습니다")
                    }
                }
            } catch (e: HttpException) {
                Timber.d("HTTP EXCEPTION")
                Resource.Error(e.localizedMessage ?: "HTTP EXCEPTION")
            } catch (e: IOException) {
                Timber.d("IO EXCEPTION")
                Resource.Error("IO EXCEPTION")
            }
        }
    }

    override fun searchEventsFlow(codeName: String, title: String): Flow<Resource<List<Event>>> {
        TODO("Not yet implemented")
    }
}