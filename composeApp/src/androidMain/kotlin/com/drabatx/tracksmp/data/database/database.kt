package com.drabatx.tracksmp.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<TracksDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<TracksDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}