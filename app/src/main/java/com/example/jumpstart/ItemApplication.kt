package com.example.jumpstart

import android.app.Application
import com.example.jumpstart.database.AppContainer
import com.example.jumpstart.database.AppDataContainer


class ItemApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppDataContainer(this)
    }
}