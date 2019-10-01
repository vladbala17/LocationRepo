package com.android.vlad.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "locations")
data class Location(

    @PrimaryKey @ColumnInfo(name = "locationid")
    var id: String = UUID.randomUUID().toString(),

    @SerializedName("lat")
    @ColumnInfo(name = "lat")
    var latitude: Double = 0.00,

    @SerializedName("long")
    @ColumnInfo(name = "long")
    var longitude: Double = 0.00,

    @ColumnInfo(name = "tag")
    var tag: String = "",

    @ColumnInfo(name = "address")
    var address: String = "",

    @SerializedName("img")
    @ColumnInfo(name = "url")
    var imageUrl: String = ""
)