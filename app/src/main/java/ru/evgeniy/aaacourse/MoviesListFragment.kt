package ru.evgeniy.aaacourse

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import java.lang.Integer.getInteger

class MoviesListFragment: Fragment() {
    private var listener: MoviesListFragmentClickListener? = null
    private var recycler: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.movieListRecycler)
        recycler?.adapter = MovieListAdapter(listener)
        recycler?.layoutManager = GridLayoutManager(context,
                if (requireContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                    requireContext().resources.getInteger(R.integer.movie_list_grid_count_portrait)
                else
                    requireContext().resources.getInteger(R.integer.movie_list_grid_count_landscape)
        )
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

    override fun onStart() {
        super.onStart()

        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? MovieListAdapter)?.apply {
            bindMovies(DataSource.getMovies(context))
        }
    }

    interface MoviesListFragmentClickListener {
        fun onMovieCardClickListener(movie: Movie)
    }
}