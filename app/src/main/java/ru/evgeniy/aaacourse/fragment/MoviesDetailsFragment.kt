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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_movies_details.*
import ru.evgeniy.aaacourse.ActorAdapter
import ru.evgeniy.aaacourse.BackButtonClickListener
import ru.evgeniy.aaacourse.R
import ru.evgeniy.aaacourse.custom.RatingBarSvg
import ru.evgeniy.aaacourse.data.Movie

class MoviesDetailsFragment : Fragment() {
    private var banner: ImageView? = null
    private var pg: TextView? = null
    private var title: TextView? = null
    private var tags: TextView? = null
    private var rating: RatingBarSvg? = null
    private var reviews: TextView? = null
    private var description: TextView? = null
    private var backButtonClickListener: BackButtonClickListener? = null
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
        tags = view?.findViewById(R.id.movieTags)
        rating = view?.findViewById(R.id.movieRating)
        reviews = view?.findViewById(R.id.movieReviews)
        description = view?.findViewById(R.id.storylineText)
        recycler = view?.findViewById(R.id.actorsRecycler)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movie = arguments?.getParcelable<Movie>(MOVIE)
        banner?.setImageDrawable(ResourcesCompat.getDrawable(requireContext().getResources(), movie!!.banner, null))
        pg?.text = movie?.pg
        title?.text = movie?.title
        tags?.text = movie?.genre
        rating?.rating = movie?.rating ?: 0f
        reviews?.text = view.context.getString(R.string.reviews, movie?.reviews.toString())
        description?.text = movie?.description

        actorsRecycler?.adapter = ActorAdapter()
        actorsRecycler?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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

    override fun onStart() {
        super.onStart()

        updateData()
    }

    fun updateData() {
        (recycler?.adapter as ActorAdapter).apply {
            movie?.let { bindActors(it.actors) }
        }
    }

    companion object {
        private const val MOVIE = "movie"

        fun newInstance(movie: Movie): MoviesDetailsFragment {
            val args = Bundle()
            args.putParcelable(MOVIE, movie)

            val fragment = MoviesDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}