package ru.evgeniy.aaacourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.evgeniy.aaacourse.data.Actor

class ActorAdapter: RecyclerView.Adapter<DataViewHolder>() {

    private var actors = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>?) {
        newActors?.let { actors = newActors }
        notifyDataSetChanged()
    }
}

class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.actorName)
    private val image: ImageView = itemView.findViewById(R.id.actorImage)

    fun onBind(actor: Actor) {
        name.text = actor.name

        Glide.with(image.context)
                .load(actor.picture)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .transform(CenterCrop(), RoundedCorners(8))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
    }
}