package com.drabatx.tracksmp.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TracksResponse(
    val banners: List<Banner>,
    val codigoRespuesta: Int,
    val mensajeRespuesta: String,
    val tracks: List<Track>
)