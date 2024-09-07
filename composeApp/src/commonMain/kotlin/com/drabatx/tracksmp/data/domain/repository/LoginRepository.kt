package com.drabatx.tracksmp.data.domain.repository

import com.drabatx.tracksmp.data.model.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import com.drabatx.tracksmp.utils.Result

interface LoginRepository {
    suspend fun login(username: String, password: String): Flow<Result<LoginResponse>>
}