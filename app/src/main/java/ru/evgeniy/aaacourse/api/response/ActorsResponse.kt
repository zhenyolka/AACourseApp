package ru.evgeniy.aaacourse.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<Actor>
)