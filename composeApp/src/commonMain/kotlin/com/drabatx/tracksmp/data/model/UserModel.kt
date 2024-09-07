package com.drabatx.tracksmp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class UserModel(
    @PrimaryKey(autoGenerate = true)val id: Int,
    val name: String? = "",
    val email: String? = "",
) {
    class Builder {
        private var id: Int = 0
        private var name: String = ""
        private var email: String = ""

        fun id(id: Int) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun email(email: String) = apply { this.email = email }

        fun build() = UserModel(id, name, email)
    }
}