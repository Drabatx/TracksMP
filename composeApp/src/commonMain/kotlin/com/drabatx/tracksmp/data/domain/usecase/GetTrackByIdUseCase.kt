package com.drabatx.tracksmp.data.domain.usecase

import com.drabatx.tracksmp.data.domain.repository.TracksRepository

class GetTrackByIdUseCase(private val tracksRepository: TracksRepository) {
    suspend operator fun invoke(trackId: Int) = tracksRepository.getTrackById(trackId)
}