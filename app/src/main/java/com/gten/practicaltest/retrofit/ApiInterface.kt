package com.gten.practicaltest.retrofit

import com.gten.practicaltest.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    fun getProductList():Call<ProductResponse>

}