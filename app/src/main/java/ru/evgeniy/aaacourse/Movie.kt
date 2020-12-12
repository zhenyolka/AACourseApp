package ru.evgeniy.aaacourse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val image: Int,
                 val name: String,
                 val genre: String,
                 val time: Int,
                 val rating: Float,
                 val reviews: Int,
                 val pg: String,
                 val isLiked: Boolean,
                 val banner: Int,
                 val title: String,
                 val description: String,
                 val actors: List<Actor>?): Parcelable
