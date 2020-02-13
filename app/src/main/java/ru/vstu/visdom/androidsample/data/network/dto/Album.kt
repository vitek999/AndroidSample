package ru.vstu.visdom.androidsample.data.network.dto

import retrofit2.http.Field

data class Album(
    @Field("id")
    val id: Long,

    @Field("userId")
    val userId: Long,

    @Field("title")
    val title: String
)