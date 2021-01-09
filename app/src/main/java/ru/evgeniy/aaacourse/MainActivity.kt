package ru.evgeniy.aaacourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.evgeniy.aaacourse.data.Movie
import ru.evgeniy.aaacourse.fragment.MoviesDetailsFragment
import ru.evgeniy.aaacourse.fragment.MoviesListFragment
import ru.evgeniy.aaacourse.util.APP_ACTIVITY

class MainActivity : AppCompatActivity(), MoviesListFragment.MoviesListFragmentClickListener, BackButtonClickListener {

    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        APP_ACTIVITY = this

        viewModel = ViewModelProvider(APP_ACTIVITY).get(MoviesViewModel::class.java)

        if(savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.mainContainer, MoviesListFragment.newInstance(), MOVIES_LIST_FRAGMENT_TAG)
                    .commit()
    }

    override fun onMovieCardClickListener(movieId: Long) {
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainContainer, MoviesDetailsFragment.newInstance(movieId), MOVIES_DETAILS_FRAGMENT_TAG)
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