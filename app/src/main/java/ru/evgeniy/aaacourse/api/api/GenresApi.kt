package ru.evgeniy.aaacourse.api.api

import retrofit2.http.GET
import ru.evgeniy.aaacourse.api.response.GenresResponse

interface GenresApi {
    @GET("genre/movie/list")
    suspend fun getGenresResponse(): GenresResponse
}