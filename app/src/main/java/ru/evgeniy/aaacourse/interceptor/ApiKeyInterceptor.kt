package ru.evgeniy.aaacourse.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.evgeniy.aaacourse.BuildConfig

class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_KEY_NAME, BuildConfig.tmdbApiKey)
                .build()

        val request = original.newBuilder()
                .url(url)
                .build()

        return chain.proceed(request)
    }

    companion object {
        const val API_KEY_NAME = "api_key"
    }
}