package com.elmilabs.scooters.scooterListing.model.repository

import com.elmilabs.scooters.scooterListing.model.entities.ScootersResponse
import io.reactivex.Observable

open class ScooterRepository constructor(
    private val dataSource: IRetrofitScooterDataSource
) {

    open fun getScooterList(): Observable<ScootersResponse> {

        return dataSource.loadScooters()

    }

}
