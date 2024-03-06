package com.caglaakgul.ecabsandroidchallenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: String,
    val type: String,
    val actor: Actor?,
    val repo: Repo?,
    @SerializedName("created_at")
    val createdAt: String
): Parcelable