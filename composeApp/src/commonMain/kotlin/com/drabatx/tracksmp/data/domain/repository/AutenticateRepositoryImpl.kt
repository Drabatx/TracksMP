package com.drabatx.tracksmp.data.domain.repository

import com.drabatx.tracksmp.data.database.UserDao
import com.drabatx.tracksmp.data.model.UserModel
import com.drabatx.tracksmp.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AutenticateRepositoryImpl(private val userDao: UserDao) :

    AutenticateRepository {
    override fun isLogged(): Flow<Boolean> = flow {
        try {
            userDao.getUser()
                .collect { user ->
                    if (user != null)
                        emit(true)
                    else
                        emit(false)
                }
        } catch (e: Exception) {
            emit(false)
        }
    }

    override fun logout(): Flow<Boolean> = flow {
        try {
            userDao.deleteAll()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    override fun getCurrentUSer(): Flow<Result<UserModel>> = flow {
        try {
            userDao.getUser()
                .collect { user ->
                    if (user != null)
                        emit(Result.Success(user))
                    else
                        emit(Result.Error(Throwable("User not found")))
                }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}