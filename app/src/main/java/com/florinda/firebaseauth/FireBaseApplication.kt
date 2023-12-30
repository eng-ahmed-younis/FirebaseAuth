package com.florinda.firebaseauth

import android.app.Application
import com.florinda.firebaseauth.di.appModule
import com.florinda.firebaseauth.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.lazyModules
import org.koin.dsl.module


class FireBaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        koinConfiguration()
    }


    private fun koinConfiguration(){
        startKoin {
            // This logger is an Android implementation of the Koin logger.
            androidLogger()
            // specify context of class that you want to start koin with it
            // provide Context instance in a [Koin module]
            androidContext(this@FireBaseApplication)
            lazyModules(
                appModule,
                viewModelModules
            )
        }
    }


    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}