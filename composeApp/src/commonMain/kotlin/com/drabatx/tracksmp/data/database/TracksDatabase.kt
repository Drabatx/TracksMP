package com.drabatx.tracksmp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.drabatx.tracksmp.data.model.UserModel
import com.drabatx.tracksmp.data.model.response.Track

const val DATABASE_NAME = "tracks_db"
interface DB{
    fun clearAllTables()
}

@Database(entities = [UserModel::class, Track::class], version = 2)
abstract class TracksDatabase : RoomDatabase(),DB {

    abstract fun userDao(): UserDao
    abstract fun trackDao(): TrackDao


    override fun clearAllTables() {}

    companion object {

    }
}