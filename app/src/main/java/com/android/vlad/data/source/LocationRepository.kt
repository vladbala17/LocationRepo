package com.android.vlad.data.source

import com.android.vlad.data.model.Location

interface LocationRepository {

    suspend fun getLocations(): Result<List<Location>>
}