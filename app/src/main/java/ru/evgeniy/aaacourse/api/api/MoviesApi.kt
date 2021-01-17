package ru.evgeniy.aaacourse.api.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.evgeniy.aaacourse.api.response.ActorsResponse
import ru.evgeniy.aaacourse.api.response.MoviesPopularResponse
import ru.evgeniy.aaacourse.api.response.RunTime

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getMoviesPopular(): MoviesPopularResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieRuntime(
        @Path("movie_id") movieId: Long
    ): RunTime

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActors(
        @Path("movie_id") movieId: Long,
    ): ActorsResponse
}