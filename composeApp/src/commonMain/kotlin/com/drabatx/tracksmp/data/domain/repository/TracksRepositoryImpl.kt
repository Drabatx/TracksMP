package com.drabatx.tracksmp.data.domain.repository

import com.drabatx.tracksmp.data.database.TrackDao
import com.drabatx.tracksmp.data.model.response.Track
import com.drabatx.tracksmp.data.model.response.TracksResponse
import com.drabatx.tracksmp.data.network.ApiService
import com.drabatx.tracksmp.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TracksRepositoryImpl(private val apiService: ApiService, private val trackDao: TrackDao) :
    TracksRepository {

    override suspend fun getTracks(): Flow<Result<TracksResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getTracks()
            trackDao.insertAll(response.tracks)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun getTrackById(trackId: Int): Flow<Result<Track>> = flow {
        emit(Result.Loading)
        try {
            trackDao.getTrackById(trackId).collect { track ->
                emit(Result.Success(track))
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}