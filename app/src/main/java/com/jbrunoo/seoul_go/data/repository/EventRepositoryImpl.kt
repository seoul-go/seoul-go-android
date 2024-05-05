package com.jbrunoo.seoul_go.data.repository

import com.jbrunoo.seoul_go.common.Resource
import com.jbrunoo.seoul_go.data.dataSource.remote.EventRemoteDataSource
import com.jbrunoo.seoul_go.data.dataSource.remote.dto.RESULT
import com.jbrunoo.seoul_go.data.dataSource.remote.dto.toEvent
import com.jbrunoo.seoul_go.data.dataSource.remote.dto.toRecentEventImage
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.domain.model.RecentEventImage
import com.jbrunoo.seoul_go.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(private val eventRemoteDataSource: EventRemoteDataSource) :
    EventRepository {
    override fun fetchEventsByCodeFlow(
        codeName: String,
        title: String
    ): Flow<Resource<List<Event>>> {
        return flow {
            emit(Resource.Loading())
            eventRemoteDataSource.fetchEventsByCodeNameFlow(codeName = codeName, title = title)
                .map { eventResponse ->
                    val result = eventResponse.culturalEventInfo.result
                    val row = eventResponse.culturalEventInfo.row

                    if (result.code == "INFO-000") {
                        Timber.d("SUCCESS")
                        val events = row.map { it.toEvent() }
                        Resource.Success(events)
                    } else {
                        handleResult(result = result)
                    }
                }.collect {
                    emit(it)
                }
        }
    }

    override fun fetchRecentEventsFlow(endIndex: Int): Flow<Resource<List<RecentEventImage>>> {
        return flow {
            emit(Resource.Loading())
            Timber.d("Loading")
            eventRemoteDataSource.fetchRecentEventsFlow(endIndex = endIndex)
                .map { eventResponse ->
                    val result = eventResponse.culturalEventInfo.result
                    val row = eventResponse.culturalEventInfo.row
                    if (result.code == "INFO-000") {
                        Timber.d("SUCCESS")
                        val eventImages = row.map { it.toRecentEventImage() }
                        Resource.Success(eventImages)
                    } else {
                        handleResult(result = result)
                    }
                }.collect {
                    emit(it)
                }
        }
    }

    private inline fun <reified T> handleResult(result: RESULT): Resource<T> {
        return try {
            when (result.code) {
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
            Resource.Error(e.localizedMessage ?: "IO EXCEPTION")
        }
    }
}