package com.dikabateingpps.todolist

import android.app.Application
import com.dikabateingpps.todolist.di.ApplicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ToDoListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(ApplicationModule)
        }
    }
}