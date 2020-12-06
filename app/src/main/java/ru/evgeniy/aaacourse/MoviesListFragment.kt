package ru.evgeniy.aaacourse

import android.content.Context
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
        recycler = view?.findViewById<RecyclerView>(R.id.movieListRecycler)
        recycler?.adapter = MovieListAdapter(listener)
        recycler?.layoutManager = GridLayoutManager(context, 2)

        val verticalDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val drawable = ResourcesCompat.getDrawable(requireContext().getResources(), R.drawable.movie_divider_vertical, null)
        verticalDecoration.setDrawable(drawable!!)
        recycler?.addItemDecoration(verticalDecoration)
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