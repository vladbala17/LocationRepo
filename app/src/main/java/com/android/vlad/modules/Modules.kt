package com.android.vlad.modules

import android.content.Context
import androidx.room.Room
import com.android.vlad.data.source.DefaultRepository
import com.android.vlad.data.source.LocationRepository
import com.android.vlad.data.source.local.LocationDatabase
import com.android.vlad.data.source.remote.LocationsApi
import com.android.vlad.locations.LocationsViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val LOCATIONS_BASE_URL = "http://demo8553475.mockable.io//"

val appModules = module {

    single {
        createLocationsService<LocationsApi>(
            okHttpClient = createHttpClient(),
            baseUrl = LOCATIONS_BASE_URL
        )
    }

    single {
        createRoomDatabase(androidApplication()).locationDao()
    }

    factory<LocationRepository> {
        DefaultRepository(locationDao = get(), locationsApi = get())
    }

    viewModel {
        LocationsViewModel(locationRepository = get())
    }
}

fun createRoomDatabase(context: Context): LocationDatabase {
    return Room.databaseBuilder(context, LocationDatabase::class.java, "LocationDatabase.db").build()
}

inline fun <reified T> createLocationsService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

