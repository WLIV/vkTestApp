package com.example.vktestapp.api

import com.example.vktestapp.entities.Response
import com.example.vktestapp.entities.SubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VkApi {
    @GET("friends.get")
   suspend fun getFriendsList(@Query("user_id") userId: String,
                               @Query("order") orderParam: String = "hints",
                               @Query("fields") fields: String = "nickname",
                               @Query("access_token") accessToken: String,
                               @Query("v") apiVersion: String = "5.81"
                               ) : SubResponse
    }