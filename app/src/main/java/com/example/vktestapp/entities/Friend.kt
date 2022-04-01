package com.example.vktestapp.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Friend(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("can_access_closed")
    val isVisible: Boolean?,
    @SerializedName("is_closed")
    val isVisibleWhenBanned: Boolean?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("track_code")
    val track_code: String?

) {
}