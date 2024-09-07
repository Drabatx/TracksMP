package com.drabatx.tracksmp.data.model.response


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Track(
    val albumId: Int,
    val albumName: String,
    val featuredPosterUrl: String,
    val name: String,
    val numTracks: Int,
    @PrimaryKey(autoGenerate = true) val phonogramId: Int,
    val posterUrl: String,
    val urlredirec: String
){
    @Ignore
    var artistName: List<String> = listOf()
}