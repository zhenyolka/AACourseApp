package ru.evgeniy.aaacourse.util

import ru.evgeniy.aaacourse.R
import ru.evgeniy.aaacourse.data.Actor
import ru.evgeniy.aaacourse.data.Movie

class DataSource {
    companion object {
        fun getMovies(): List<Movie> = listOf(
            Movie(
                R.drawable.movie1,
                "Avengers: End Game",
                "Action, Adventure, Fantasy",
                137,
                4f,
                125,
                "13+",
                false,
                R.drawable.movie1_banner,
                "Avengers:\nEnd Game",
                "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                listOf(
                        Actor("Robert Downey Jr.", R.drawable.actor1),
                        Actor("Chris\nEvans", R.drawable.actor2),
                        Actor("Mark\nRuffalo", R.drawable.actor3),
                        Actor("Chris\nHemsworth", R.drawable.actor4)
                )),
            Movie(
                R.drawable.movie2,
                "Tenet",
                "Action, Sci-Fi, Thriller",
                97,
                5f,
                98,
                "16+",
                true, R.drawable.movie2,
                "Tenet",
                "Example",
                null),
            Movie(
                R.drawable.movie3,
                "Black Widow",
                "Action, Adventure, Sci-Fi",
                102,
                4f,
                38,
                "13+",
                false, R.drawable.movie3,
                "Black Widow",
                "Example",
                null),
            Movie(
                R.drawable.movie4,
                "Wonder Woman 1984",
                "Action, Adventure, Fantasy",
                120,
                5f,
                74,
                "13+",
                false, R.drawable.movie4,
                "Wonder Woman 1984",
                "Example",
                null)
        )
    }
}