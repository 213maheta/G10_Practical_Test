package com.gten.practicaltest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://dummyjson.com/"
    val retrofit:Retrofit by lazy { createClient() }

    fun createClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun getClient(): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

}