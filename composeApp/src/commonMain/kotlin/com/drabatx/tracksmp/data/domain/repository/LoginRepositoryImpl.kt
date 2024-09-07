package com.drabatx.tracksmp.data.domain.repository

import com.drabatx.tracksmp.data.database.UserDao
import com.drabatx.tracksmp.data.model.UserModel
import com.drabatx.tracksmp.data.model.response.LoginResponse
import com.drabatx.tracksmp.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(private val userDao: UserDao) : LoginRepository {
    override suspend fun login(username: String, password: String): Flow<Result<LoginResponse>> =
        flow {
            emit(Result.Loading)
            try {
                if (username=="speedymovil" && password=="speedymovil2024"){
                    val user= UserModel(1,username,username)
                    userDao.saveUser(user) // Guardar la información del usuario
                    emit(Result.Success(LoginResponse(message = "Login exitoso", userModel = user)))
                }else{
                    emit(Result.Error(Throwable("No se encontro usuario registrado")))

                }
            } catch (e: Exception) {
                println(e.message)
                emit(Result.Error(e))
            }
        }
}