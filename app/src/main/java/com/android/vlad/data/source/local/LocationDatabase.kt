package com.android.vlad.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.vlad.data.model.Location

@Database(entities = [Location::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

}