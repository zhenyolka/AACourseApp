package ru.evgeniy.aaacourse.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(val id: Long, val name: String): Parcelable