package ru.evgeniy.aaacourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView

class MovieListAdapter(): RecyclerView.Adapter<MovieViewHolder>() {
    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovie: List<Movie>) {
        movies = newMovie
    }
}

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val image: ImageView? = itemView.findViewById(R.id.movieImage)
    private val name: TextView? = itemView.findViewById(R.id.movieNameMain)
    private val genre: TextView? = itemView.findViewById(R.id.movieTagsMain)
    private val time: TextView? = itemView.findViewById(R.id.movieMinutesText)
    private val rating: RatingBar? = itemView.findViewById(R.id.movieRatingMain)
    private val reviews: TextView? = itemView.findViewById(R.id.movieReviewsMain)
    private val pg: TextView? = itemView.findViewById(R.id.pgMain)
    private val like: AppCompatImageButton? = itemView.findViewById(R.id.likeButton)


    fun onBind(movie: Movie) {
        image?.setImageDrawable(movie.image)

        name?.text = movie.name

        genre?.text = movie.genre

        time?.text = "${movie.time} MIN"

        rating?.rating = movie.rating

        reviews?.text = "${movie.reviews} Reviews"

        pg?.text = movie.pg

        like?.setImageResource(if (movie.isLiked)
            R.drawable.ic_icon_like_activated
        else
                R.drawable.ic_icon_like_normal)
    }
}