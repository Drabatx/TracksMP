package com.drabatx.tracksmp.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Banner(
    val albumId: String,
    val albumName: String,
    val id: Int,
    val image: String,
    val urlRedirect: String
)