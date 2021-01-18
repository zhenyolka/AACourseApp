package ru.evgeniy.aaacourse.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName("name")
    val name: String,
    @SerialName("id")
    val id: Long
)
