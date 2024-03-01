package com.ahuynh.harrypotterapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wand(
    val core: String,
    val length: Double,
    val wood: String
) : Parcelable