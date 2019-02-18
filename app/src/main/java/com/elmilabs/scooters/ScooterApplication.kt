package com.elmilabs.scooters

import android.app.Application
import com.elmilabs.scooters.di.DaggerMainComponent
import com.elmilabs.scooters.di.MainComponent

class ScooterApplication : Application(){
    lateinit var graph: MainComponent

    override fun onCreate() {
        super.onCreate()
        graph = DaggerMainComponent.builder().build()

    }




}