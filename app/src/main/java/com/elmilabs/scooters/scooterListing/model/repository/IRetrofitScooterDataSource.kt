package com.elmilabs.scooters.scooterListing.model.repository

import com.elmilabs.scooters.scooterListing.model.entities.ScootersResponse
import io.reactivex.Observable

interface IRetrofitScooterDataSource {
    fun loadScooters(): Observable<ScootersResponse>

}
