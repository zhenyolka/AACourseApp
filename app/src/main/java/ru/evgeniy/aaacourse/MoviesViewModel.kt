package ru.evgeniy.aaacourse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.evgeniy.aaacourse.data.Movie
import ru.evgeniy.aaacourse.data.loadMovies
import ru.evgeniy.aaacourse.util.APP_ACTIVITY

class MoviesViewModel: ViewModel() {
    private val _moviesList = MutableLiveData<Map<Long, Movie>>()
    val moviesList: LiveData<Map<Long, Movie>> get() = _moviesList

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _moviesList.postValue(loadMovies(APP_ACTIVITY).associateBy { it.id })
        }
    }

    fun getMovie(movieId: Long) = _moviesList.value?.get(movieId)
}