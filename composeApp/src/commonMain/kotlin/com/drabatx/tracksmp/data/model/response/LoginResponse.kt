package com.drabatx.tracksmp.data.model.response

import com.drabatx.tracksmp.data.model.UserModel
import kotlinx.serialization.Serializable

//
@Serializable
data class LoginResponse(val message: String, val userModel: UserModel)