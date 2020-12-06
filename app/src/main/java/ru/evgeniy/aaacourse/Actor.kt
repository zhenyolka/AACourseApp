package ru.evgeniy.aaacourse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(val actor: String,
                 val image: Int): Parcelable
