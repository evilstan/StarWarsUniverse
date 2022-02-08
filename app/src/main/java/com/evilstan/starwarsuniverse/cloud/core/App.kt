package com.evilstan.starwarsuniverse.cloud.core

import android.app.Application
import com.evilstan.starwarsuniverse.data.database.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = AppDatabase.getInstance(this)
    }

    companion object {
        lateinit var instance: Application
            private set
        lateinit var database: AppDatabase
            private set
    }
}