package com.elmilabs.scooters.di

import com.elmilabs.scooters.scooterListing.ScooterListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(scooterListFragment: ScooterListFragment)

}