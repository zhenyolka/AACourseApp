package ru.evgeniy.aaacourse.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import ru.evgeniy.aaacourse.MainActivity
import ru.evgeniy.aaacourse.MovieListAdapter
import ru.evgeniy.aaacourse.R
import ru.evgeniy.aaacourse.data.Movie

class MoviesListFragment: Fragment(),
    Observer<Map<Long, Movie>> {
    private var listener: MoviesListFragmentClickListener? = null
    private var recycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

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
                    requireContext().resources.getInteger(R.integer.movie_list_grid_count)
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

        (activity as MainActivity).viewModel
            .moviesList
            .observe(this.viewLifecycleOwner, this)
    }

    private fun updateData(movies: List<Movie>) {
        (recycler?.adapter as? MovieListAdapter)?.apply {
            bindMovies(movies)
        }
    }

    companion object {
        fun newInstance(): MoviesListFragment {
            val fragment = MoviesListFragment()
            return fragment
        }
    }

    interface MoviesListFragmentClickListener {
        fun onMovieCardClickListener(movieId: Long)
    }

    override fun onChanged(t: Map<Long, Movie>?) {
        (recycler
            ?.adapter as MovieListAdapter)
            .bindMovies((activity as MainActivity)
                .viewModel.moviesList.value?.values?.toList())
    }
}