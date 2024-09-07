package com.drabatx.tracksmp.data.domain.usecase

import com.drabatx.tracksmp.data.domain.repository.TracksRepository

class GetTrackListUseCase (private val tracksRepository: TracksRepository){
    suspend operator fun invoke() = tracksRepository.getTracks()
}