package ru.evgeniy.aaacourse.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import androidx.transition.Visibility
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.fragment_movies_details.*
import ru.evgeniy.aaacourse.*
import ru.evgeniy.aaacourse.custom.RatingBarSvg
import ru.evgeniy.aaacourse.data.Movie
import ru.evgeniy.aaacourse.util.APP_ACTIVITY

class MoviesDetailsFragment : Fragment() {
    private var banner: ImageView? = null
    private var pg: TextView? = null
    private var title: TextView? = null
    private var runtime: TextView? = null
    private var tags: TextView? = null
    private var rating: RatingBarSvg? = null
    private var reviews: TextView? = null
    private var description: TextView? = null
    private var backButtonClickListener: BackButtonClickListener? = null
    private var cast: TextView? = null
    private var recycler: RecyclerView? = null

    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        view?.findViewById<AppCompatButton>(R.id.backButton)
                ?.setOnClickListener{
                    backButtonClickListener?.onBackButtonPressed()
                }
        banner = view?.findViewById(R.id.movieBanner)
        pg = view?.findViewById(R.id.pg)
        title = view?.findViewById(R.id.movieName)
        runtime = view?.findViewById(R.id.movieMinutesText)
        tags = view?.findViewById(R.id.movieTags)
        rating = view?.findViewById(R.id.movieRating)
        reviews = view?.findViewById(R.id.movieReviews)
        description = view?.findViewById(R.id.storylineText)
        cast = view?.findViewById(R.id.castName)
        recycler = view?.findViewById(R.id.actorsRecycler)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        actorsRecycler?.adapter = ActorAdapter()
        actorsRecycler?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        arguments?.apply {
            val movieId = getLong(MOVIE_ID)
            val viewModel = ViewModelProvider(APP_ACTIVITY).get(MoviesViewModel::class.java)
            movie = (activity as MainActivity).viewModel.getMovie(movieId)
            banner?.apply { Glide.with(requireContext())
                .load(movie?.backdrop)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this) }
            pg?.text = context?.getString(R.string.pg, movie?.minimumAge)
            title?.text = movie?.title
            tags?.text = movie?.genres?.joinToString(separator = ", ", transform = {it.name})
            rating?.rating = movie?.ratings?.apply { div(2) }?.toFloat() ?: 0f
            reviews?.text = view.context.getString(R.string.reviews, movie?.numberOfRatings)
            description?.text = movie?.overview
            viewModel.getMovieActors(movieId).observe(
                viewLifecycleOwner, { actors ->
                    cast?.visibility = if (actors.isEmpty()) View.GONE else View.VISIBLE
                    (recycler?.adapter as ActorAdapter).apply {
                        movie?.let { bindActors(actors) }
                    }
                }
            )
            viewModel.getMovieRuntime(movieId).observe(
                viewLifecycleOwner, {
                    runtime?.text = getString(R.string.minutes, it)
                }
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BackButtonClickListener) {
            backButtonClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        backButtonClickListener = null
    }

    companion object {
        private const val MOVIE_ID = "movie"

        fun newInstance(movieId: Long): MoviesDetailsFragment {
            val args = Bundle()
            args.putLong(MOVIE_ID, movieId)

            val fragment = MoviesDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}