package com.drabatx.tracksmp.data.domain.usecase

import com.drabatx.tracksmp.data.domain.repository.LoginRepository
import com.drabatx.tracksmp.data.model.response.LoginResponse
import com.drabatx.tracksmp.utils.Result
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(userName: String, password: String): Flow<Result<LoginResponse>> {
        return repository.login(userName, password)
    }
}