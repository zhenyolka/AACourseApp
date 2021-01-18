package ru.evgeniy.aaacourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.evgeniy.aaacourse.custom.RatingBarSvg
import ru.evgeniy.aaacourse.data.Movie
import ru.evgeniy.aaacourse.fragment.MoviesListFragment
import kotlin.math.roundToInt

class MovieListAdapter(val listener: MoviesListFragment.MoviesListFragmentClickListener?):
    RecyclerView.Adapter<MovieViewHolder>() {
    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener{
            listener?.onMovieCardClickListener(movies[position].id)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovie: List<Movie>?) {
        newMovie?.let {
            movies = newMovie
            notifyDataSetChanged()
        }
    }
}

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.movieImage)
    private val name: TextView = itemView.findViewById(R.id.movieNameMain)
    private val genre: TextView = itemView.findViewById(R.id.movieTagsMain)
    private val rating: RatingBarSvg = itemView.findViewById(R.id.movieRatingMain)
    private val reviews: TextView = itemView.findViewById(R.id.movieReviewsMain)
    private val pg: TextView = itemView.findViewById(R.id.pgMain)
    private val like: AppCompatImageButton = itemView.findViewById(R.id.likeButton)


    fun onBind(movie: Movie) {
        Glide.with(image.context)
                .load(movie.poster)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .transform(CenterCrop(), RoundedCorners(16))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)

        name.text = movie.title

        genre.text = movie.genres.joinToString (separator = ", ", transform = { it.name })

        rating.rating = (movie.ratings / 2).toFloat()

        reviews.text = itemView.context.getString(R.string.reviews, movie.numberOfRatings)

        pg.text = itemView.context.getString(R.string.pg, movie.minimumAge)

        like.setImageResource(if (movie.isLiked)
            R.drawable.ic_like_enabled
        else
                R.drawable.ic_like_disabled)
    }
}