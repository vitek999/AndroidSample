package ru.vstu.visdom.androidsample.data.network.endpoints

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import ru.vstu.visdom.androidsample.data.network.dto.Album

interface AlbumEndpoint {

    @GET("albums/{albumId}")
    fun getAlbumById(@Path("albumId") albumId: Long) : Deferred<Album>

}