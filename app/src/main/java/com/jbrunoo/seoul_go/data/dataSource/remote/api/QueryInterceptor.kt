package com.jbrunoo.seoul_go.data.dataSource.remote.api

import android.util.Log
import com.jbrunoo.seoul_go.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentUrl = chain.request().url
        val newUrl = currentUrl.newBuilder().apply {
            setPathSegment(0, BuildConfig.SEOUL_API_KEY)
            setPathSegment(1, "json")
            setPathSegment(2, "culturalEventInfo")
            setPathSegment(3, "1")
        }.build()

        val currentRequest = chain.request().newBuilder()
        val newRequest = currentRequest.url(newUrl).build()
        return chain.proceed(newRequest)
    }
}