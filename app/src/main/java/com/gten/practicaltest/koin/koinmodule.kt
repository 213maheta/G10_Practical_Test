package com.gten.practicaltest.koin

import com.gten.practicaltest.repository.MainRepository
import com.gten.practicaltest.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mymodule = module{

    single {  MainRepository() }
    viewModel { MainViewModel(get()) }
}