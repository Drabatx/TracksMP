package com.drabatx.tracksmp.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<TracksDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<TracksDatabase>(
        name = dbFilePath,
        factory =  { TracksDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
}