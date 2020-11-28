package ru.evgeniy.aaacourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentMoviesList.FragmentMoviesListClickListener, BackButtonClickListener {

    private var moviesListFragment: FragmentMoviesList? = null
    private var moviesDetailsFragment: FragmentMoviesDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.mainContainer, FragmentMoviesList(), MOVIES_LIST_FRAGMENT_TAG)
                    .commit()
        } else {
            moviesListFragment = supportFragmentManager
                    .findFragmentByTag(MOVIES_LIST_FRAGMENT_TAG) as? FragmentMoviesList
            moviesDetailsFragment = supportFragmentManager
                    .findFragmentByTag(MOVIES_DETAILS_FRAGMENT_TAG) as? FragmentMoviesDetails
        }
    }

    override fun onMovieCardClickListener() {
        if (moviesDetailsFragment == null)
            moviesDetailsFragment = FragmentMoviesDetails()
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