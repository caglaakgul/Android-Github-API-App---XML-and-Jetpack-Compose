package com.caglaakgul.ecabsandroidchallenge.data.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val private: Boolean,
    @SerializedName("html_url")
    val htmlUrl: String
)
