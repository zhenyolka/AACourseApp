package ru.evgeniy.aaacourse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class FragmentMoviesList: Fragment() {
    private var fragmentMoviesListClickListener: FragmentMoviesListClickListener? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        view?.findViewById<ConstraintLayout>(R.id.movieCard)
                ?.setOnClickListener{
                    fragmentMoviesListClickListener?.onMovieCardClickListener()
                }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentMoviesListClickListener) {
            fragmentMoviesListClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentMoviesListClickListener = null
    }

    interface FragmentMoviesListClickListener {
        fun onMovieCardClickListener()
    }
}