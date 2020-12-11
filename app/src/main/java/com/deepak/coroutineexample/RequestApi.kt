package com.deepak.coroutineexample

import retrofit2.Call
import retrofit2.http.GET

interface RequestApi {

    @GET("/comments")
    fun getComments(): Call<List<Comment>>

    //Second way to return response and not call
//    @GET("/comments")
//    suspend fun getComments(): List<Comment>
}