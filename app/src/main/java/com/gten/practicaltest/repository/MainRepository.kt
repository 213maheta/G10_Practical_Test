package com.gten.practicaltest.repository

import com.gten.practicaltest.model.ProductResponse
import com.gten.practicaltest.retrofit.RetrofitClient
import com.gten.practicaltest.sealed.ResponseType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    private var _responseType = MutableStateFlow<ResponseType>(ResponseType.EMPTY)
    var responseType = _responseType.asStateFlow()

    fun getProductList()
    {
        _responseType.value = ResponseType.Loading

        RetrofitClient.getClient().getProductList().enqueue(object : Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if(response.isSuccessful)
                {
                    _responseType.value = response.body()?.products?.let { ResponseType.Success(it) }?:ResponseType.Fail("No Produscts available")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                _responseType.value =  ResponseType.Fail(t.toString())
            }
        })
    }
}