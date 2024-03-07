package com.caglaakgul.ecabsandroidchallenge.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HttpTokenInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("X-GitHub-Api-Version", "2022-11-28")
            .build()
        return chain.proceed(request)
    }
}