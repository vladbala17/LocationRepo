package com.android.vlad.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.vlad.data.model.Location

@Dao
interface LocationDao {

    @Query("Select * from locations")
    fun getLocations(): List<Location>

    @Query("Select * from locations where locationid = :id")
    fun getLocationById(id: String): LiveData<Location>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location)

    @Query("Delete from locations where locationid = :id")
    suspend fun deleteLocationById(id: String): Int

    @Update
    suspend fun updateLocation(location: Location)

    @Query("Delete from locations")
    suspend fun clearData()
}