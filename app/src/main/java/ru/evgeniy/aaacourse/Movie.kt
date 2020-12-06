package ru.evgeniy.aaacourse

import android.graphics.drawable.Drawable

data class Movie(val image: Drawable?,
                 val name: String,
                 val genre: String,
                 val time: Int,
                 val rating: Float,
                 val reviews: Int,
                 val pg: String,
                 val isLiked: Boolean)
