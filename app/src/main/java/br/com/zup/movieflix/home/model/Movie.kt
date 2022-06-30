package br.com.zup.movieflix.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var title: String,
    var description: String,
    var image: Int,
    var director:String,
    var accessAuth : Boolean = false
): Parcelable