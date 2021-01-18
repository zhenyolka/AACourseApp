package ru.evgeniy.aaacourse.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import ru.evgeniy.aaacourse.BuildConfig
import ru.evgeniy.aaacourse.api.api.ConfigApi
import ru.evgeniy.aaacourse.api.api.GenresApi
import ru.evgeniy.aaacourse.api.api.MoviesApi
import ru.evgeniy.aaacourse.interceptor.ApiKeyInterceptor
import ru.evgeniy.aaacourse.interceptor.LanguageInterceptor
import ru.evgeniy.aaacourse.util.BASE_API_URL

object NetworkModule {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private fun buildOkHttpClient(): OkHttpClient {
        val client = OkHttpClient().newBuilder().apply {
            addInterceptor(ApiKeyInterceptor())
            addInterceptor(LanguageInterceptor())
            if (BuildConfig.DEBUG)
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return client.build()
    }

    @ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .client(buildOkHttpClient())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val moviesApi: MoviesApi = retrofit.create()
    val genresApi: GenresApi = retrofit.create()
    val configApi: ConfigApi = retrofit.create()
}