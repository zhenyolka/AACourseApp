package ru.evgeniy.aaacourse.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RunTime(
    @SerialName("runtime")
    val runtime: Int
)
