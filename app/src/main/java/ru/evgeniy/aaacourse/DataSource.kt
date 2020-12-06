package ru.evgeniy.aaacourse

import android.content.Context
import androidx.core.content.res.ResourcesCompat

class DataSource {
    companion object {
        fun getMovies(context: Context?): List<Movie> = listOf(
            Movie(ResourcesCompat.getDrawable(context!!.getResources(), R.drawable.movie1, null),
                "Avengers: End Game",
                "Action, Adventure, Fantasy",
                137,
                4f,
                125,
                "13+",
                false),
            Movie(ResourcesCompat.getDrawable(context.getResources(), R.drawable.movie2, null),
                "Tenet",
                "Action, Sci-Fi, Thriller",
                97,
                5f,
                98,
                "16+",
                true),
            Movie(ResourcesCompat.getDrawable(context.getResources(), R.drawable.movie3, null),
                "Black Widow",
                "Action, Adventure, Sci-Fi",
                102,
                4f,
                38,
                "13+",
                false),
            Movie(ResourcesCompat.getDrawable(context.getResources(), R.drawable.movie4, null),
                "Wonder Woman 1984",
                "Action, Adventure, Fantasy",
                120,
                5f,
                74,
                "13+",
                false)
        )
    }
}