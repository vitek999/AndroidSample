package ru.vstu.visdom.androidsample.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.vstu.visdom.androidsample.data.network.endpoints.AlbumEndpoint

object AlbumNetwork {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val albumService = retrofit.create(AlbumEndpoint::class.java)
}