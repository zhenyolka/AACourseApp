package ru.evgeniy.aaacourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.evgeniy.aaacourse.data.Movie
import ru.evgeniy.aaacourse.fragment.MoviesDetailsFragment
import ru.evgeniy.aaacourse.fragment.MoviesListFragment

class MainActivity : AppCompatActivity(), MoviesListFragment.MoviesListFragmentClickListener, BackButtonClickListener {

    private var moviesListFragment: MoviesListFragment? = null
    private var moviesDetailsFragment: MoviesDetailsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.mainContainer, MoviesListFragment(), MOVIES_LIST_FRAGMENT_TAG)
                    .commit()
        } else {
            moviesListFragment = supportFragmentManager
                    .findFragmentByTag(MOVIES_LIST_FRAGMENT_TAG) as? MoviesListFragment
            moviesDetailsFragment = supportFragmentManager
                    .findFragmentByTag(MOVIES_DETAILS_FRAGMENT_TAG) as? MoviesDetailsFragment
        }
    }

    override fun onMovieCardClickListener(movie: Movie) {
        moviesDetailsFragment = MoviesDetailsFragment.newInstance(movie)
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainContainer, moviesDetailsFragment!!, MOVIES_DETAILS_FRAGMENT_TAG)
                .commit()
    }

    override fun onBackButtonPressed() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        const val MOVIES_LIST_FRAGMENT_TAG = "MOVIES_LIST_FRAGMENT_TAG"
        const val MOVIES_DETAILS_FRAGMENT_TAG = "MOVIES_DETAILS_FRAGMENT_TAG"
    }
}