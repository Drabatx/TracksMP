package com.drabatx.tracksmp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.drabatx.tracksmp.data.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user:UserModel)

    @Query("SELECT * FROM UserModel LIMIT 1")
    fun getUser(): Flow<UserModel?>

    @Query("DELETE FROM UserModel")
    suspend fun deleteAll()
}