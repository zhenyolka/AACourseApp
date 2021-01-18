package ru.evgeniy.aaacourse.data

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Double,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val isLiked: Boolean = false,
    val genres: List<Genre>
)