package com.example.vktestapp.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("items")
    val items: List<Friend>?
    ) {
}