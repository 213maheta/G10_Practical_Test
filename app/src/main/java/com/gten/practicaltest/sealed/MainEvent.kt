package com.gten.practicaltest.sealed

import com.gten.practicaltest.model.Products

sealed class MainEvent{
    object ShowProgressBar:MainEvent()
    object Idle:MainEvent()
    data class SetProductList(val list: List<Products>):MainEvent()
    data class ShowToast(val message:String):MainEvent()
}
