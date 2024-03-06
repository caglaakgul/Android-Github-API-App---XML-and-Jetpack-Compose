package com.caglaakgul.ecabsandroidchallenge.data.remote

import com.caglaakgul.ecabsandroidchallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HttpTokenInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
            .addHeader("X-GitHub-Api-Version", "2022-11-28")
            .build()
        return chain.proceed(request)
    }
}