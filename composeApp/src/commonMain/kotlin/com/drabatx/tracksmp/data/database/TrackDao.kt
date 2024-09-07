package com.drabatx.tracksmp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.drabatx.tracksmp.data.model.response.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tracks: List<Track>)

    @Query("SELECT * FROM Track")
    fun getAllTracks(): Flow<List<Track>>

    @Query("SELECT * FROM Track WHERE phonogramId = :id")
    fun getTrackById(id: Int): Flow<Track>
}