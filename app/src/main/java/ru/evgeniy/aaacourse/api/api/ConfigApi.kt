package ru.evgeniy.aaacourse.api.api

import retrofit2.http.GET
import ru.evgeniy.aaacourse.api.response.ConfigResponse

interface ConfigApi {
    @GET("configuration")
    suspend fun getConfig(): ConfigResponse
}