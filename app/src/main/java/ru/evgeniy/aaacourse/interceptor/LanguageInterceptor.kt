package ru.evgeniy.aaacourse.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class LanguageInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter(LANGUAGE_NAME, Locale.getDefault().getLanguage())
                .build()

        val request = original.newBuilder()
                .url(url)
                .build()

        return chain.proceed(request)
    }

    companion object {
        const val LANGUAGE_NAME = "language"
    }
}