package br.com.zup.movieflix.moviedetail.model

import android.media.Image
import android.os.Parcelable
import android.widget.ImageView
import br.com.zup.movieflix.home.model.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DirectorModel(
    var name: String,
    var info: String,
    var accessAuth : Boolean = false
) : Parcelable