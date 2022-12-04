package com.gten.practicaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gten.practicaltest.repository.MainRepository
import com.gten.practicaltest.sealed.MainEvent
import com.gten.practicaltest.sealed.ResponseType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val repository: MainRepository):ViewModel() {

    private var _event = MutableStateFlow<MainEvent>(MainEvent.Idle)
    var event = _event.asStateFlow()

    fun setObserver() = viewModelScope.launch {
        repository.responseType.collect{
            when(it)
            {
                ResponseType.Loading->{
                    _event.value = MainEvent.ShowProgressBar
                }
                is ResponseType.Success->{

                   _event.value = MainEvent.SetProductList(it.productList)
                }
                is ResponseType.Fail->{
                    _event.value = MainEvent.ShowToast(it.message)
                }
                else -> {}
            }
        }
    }

    fun getProductList()
    {
        repository.getProductList()
    }

}