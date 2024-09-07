package com.drabatx.tracksmp.data.domain.usecase

import com.drabatx.tracksmp.data.domain.repository.AutenticateRepository

class LogOutUseCase (private val repository: AutenticateRepository) {
    operator fun invoke() = repository.logout()
}

