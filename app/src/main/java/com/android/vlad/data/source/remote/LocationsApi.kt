package com.android.vlad.data.source.remote

import com.android.vlad.data.model.Location
import retrofit2.http.GET

interface LocationsApi {

    @GET("/mylocations")
    suspend fun getLocations() : List<Location>
}