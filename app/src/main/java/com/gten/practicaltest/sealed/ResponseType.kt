package com.gten.practicaltest.sealed

import com.gten.practicaltest.model.Products

sealed class ResponseType{
    class Success(val productList: List<Products>):ResponseType()
    class Fail(val message: String):ResponseType()
    object Loading:ResponseType()
    object EMPTY:ResponseType()
}
