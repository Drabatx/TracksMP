package com.drabatx.tracksmp.data.domain.repository

import com.drabatx.tracksmp.data.model.UserModel
import com.drabatx.tracksmp.utils.Result
import kotlinx.coroutines.flow.Flow

interface AutenticateRepository {
    fun isLogged(): Flow<Boolean>
    fun logout(): Flow<Boolean>
    fun getCurrentUSer():Flow<Result<UserModel>>
}