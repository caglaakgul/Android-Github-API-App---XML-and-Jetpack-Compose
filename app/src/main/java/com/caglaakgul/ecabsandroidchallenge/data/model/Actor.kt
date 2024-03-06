package com.caglaakgul.ecabsandroidchallenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String
): Parcelable
