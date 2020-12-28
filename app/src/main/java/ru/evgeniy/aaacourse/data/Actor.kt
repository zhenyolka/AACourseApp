package ru.evgeniy.aaacourse.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(
    val id: Long,
    val name: String,
    val picture: String
): Parcelable