package com.example.ikme_app

import android.app.Application
import com.example.ikme_app.data.ServiceContainer
import com.example.ikme_app.data.ServiceContainerImpl

class TodoApplication : Application() {

    lateinit var container: ServiceContainer

    override fun onCreate() {
        super.onCreate()
        container = ServiceContainerImpl(this)
    }
}