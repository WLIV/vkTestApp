package com.example.vktestapp.repository

import com.example.vktestapp.api.VkApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.internal.http2.Http2Connection
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class VkApiClient {
    companion object {

        private val instance: VkApi by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.vk.com/method/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(VkApi::class.java)

        }
        fun getVkApiClientInstance() : VkApi = VkApiClient.instance
    }
}