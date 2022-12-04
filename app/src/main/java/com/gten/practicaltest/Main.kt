package com.gten.practicaltest

import android.app.Application
import com.gten.practicaltest.koin.mymodule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Main: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@Main)
            // declare modules
            modules(mymodule)
        }
    }

}