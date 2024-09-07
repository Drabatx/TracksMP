package com.drabatx.tracksmp.data.domain.repository

import com.drabatx.tracksmp.data.model.response.Track
import com.drabatx.tracksmp.data.model.response.TracksResponse
import com.drabatx.tracksmp.utils.Result
import kotlinx.coroutines.flow.Flow

interface TracksRepository {
    suspend fun getTracks(): Flow<Result<TracksResponse>>
    suspend fun getTrackById(trackId: Int): Flow<Result<Track>>
}