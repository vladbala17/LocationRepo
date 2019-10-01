package com.android.vlad.data.source

import com.android.vlad.data.model.Location
import com.android.vlad.data.source.local.LocationDao
import com.android.vlad.data.source.remote.LocationsApi

class DefaultRepository(
    private val locationDao: LocationDao,
    private val locationsApi: LocationsApi
) : LocationRepository {

    override suspend fun getLocations(): Result<List<Location>> {
        return try {
            val locationsResult = locationsApi.getLocations()
            locationDao.clearData()

            for (location in locationsResult) {
                locationDao.insertLocation(location)
            }

            val locationDb = locationDao.getLocations()
            return Result.Success(locationDb)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}