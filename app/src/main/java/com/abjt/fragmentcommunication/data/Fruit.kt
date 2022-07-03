package com.abjt.fragmentcommunication.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fruit(val name: String, @DrawableRes val icon: Int, @DrawableRes val cover: Int) : Parcelable
