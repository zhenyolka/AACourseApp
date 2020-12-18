package ru.evgeniy.aaacourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.evgeniy.aaacourse.data.Actor

class ActorAdapter: RecyclerView.Adapter<ActorViewHolder>() {

    private var actors = listOf<Actor>()

    override fun getItemViewType(position: Int): Int = if (actors.isEmpty()) EMPTY_VIEW_TYPE else DATA_VIEW_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return when(viewType) {
            EMPTY_VIEW_TYPE -> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor_empty, parent, false))
            else -> DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor_data, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        when(holder){
            is EmptyViewHolder -> {}
            is DataViewHolder -> {holder.onBind(actors[position])}
        }
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>?) {
        newActors?.let { actors = newActors }
        notifyDataSetChanged()
    }
}

abstract class ActorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

class EmptyViewHolder(itemView: View): ActorViewHolder(itemView)
class DataViewHolder(itemView: View): ActorViewHolder(itemView) {
    private val name: TextView? = itemView.findViewById(R.id.actorName)
    private val image: ImageView? = itemView.findViewById(R.id.actorImage)

    fun onBind(actor: Actor) {
        name?.text = actor.name

        image?.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), actor.image, null))
    }
}

private const val EMPTY_VIEW_TYPE = 0
private const val DATA_VIEW_TYPE = 1