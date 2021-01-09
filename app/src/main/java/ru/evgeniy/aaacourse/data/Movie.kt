package ru.evgeniy.aaacourse.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val runtime: Int,
    val isLiked: Boolean = false,
    val genres: List<Genre>,
    val actors: List<Actor>
): Parcelable