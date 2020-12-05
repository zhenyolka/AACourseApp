package ru.evgeniy.aaacourse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class MoviesListFragment: Fragment() {
    private var listener: MoviesListFragmentClickListener? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        view?.findViewById<ConstraintLayout>(R.id.movieCard)
                ?.setOnClickListener{
                    listener?.onMovieCardClickListener()
                }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MoviesListFragmentClickListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface MoviesListFragmentClickListener {
        fun onMovieCardClickListener()
    }
}