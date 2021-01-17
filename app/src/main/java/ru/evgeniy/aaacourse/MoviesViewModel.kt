package ru.evgeniy.aaacourse

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch
import ru.evgeniy.aaacourse.api.NetworkModule
import ru.evgeniy.aaacourse.api.response.ConfigResponse
import ru.evgeniy.aaacourse.data.Actor
import ru.evgeniy.aaacourse.data.Genre
import ru.evgeniy.aaacourse.data.Movie

class MoviesViewModel: ViewModel() {
    private val _moviesList = MutableLiveData<Map<Long, Movie>>()
    val moviesList: LiveData<Map<Long, Movie>> get() = _moviesList

    private val _config = MutableLiveData<ConfigResponse>()
    val config: LiveData<ConfigResponse> get() = _config

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            Log.d(
                "MoviesViewModel",
                "$exception"
            )
        }){
            _config.postValue(NetworkModule.configApi.getConfig())
            val genres = NetworkModule.genresApi.getGenresResponse().genres.map {
                Genre(
                    it.id,
                    it.name
                )
            }.associateBy { it.id }

            val movies = NetworkModule.moviesApi.getMoviesPopular().results.map {
                Movie(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster = _config.value!!.images.secureBaseUrl
                            + _config.value!!.images.posterSizes[2]
                            + it.posterPath,
                    backdrop = _config.value!!.images.secureBaseUrl
                            + _config.value!!.images.backdropSizes[2] + it.backdropPath,
                    it.voteAverage,
                    numberOfRatings = it.voteCount,
                    minimumAge = if (it.adult) 18 else 12,
                    genres = it.genreIds.map {
                        Genre(
                            it,
                            genres[it]?.name ?: "Unkown genre"
                        )
                    }
                )
            }

            _moviesList.postValue(movies.associateBy { it.id })
        }
    }

    fun getMovie(movieId: Long) = _moviesList.value?.get(movieId)

    fun getMovieRuntime(movieId: Long): LiveData<Int> {
        val runtime = MutableLiveData<Int>()
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            Log.d(
                "MoviesViewModel",
                "$exception"
            )
        }){
            runtime.postValue(NetworkModule.moviesApi.getMovieRuntime(movieId).runtime)
        }
        return runtime
    }

    fun getMovieActors(movieId: Long): LiveData<List<Actor>> {
        val actorsList = MutableLiveData<List<Actor>>()
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            Log.d(
                "MoviesViewModel",
                "$exception"
            )
        }){
            actorsList.postValue(NetworkModule.moviesApi.getMovieActors(movieId).cast.map {
                Actor(
                    it.id,
                    it.name,
                    _config.value!!.images.secureBaseUrl
                            + _config.value!!.images.profileSizes[2] + it.profilePath
                )
            })
        }
        return actorsList
    }
}