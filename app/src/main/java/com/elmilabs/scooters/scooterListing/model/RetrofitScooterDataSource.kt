package com.elmilabs.scooters.scooterListing.model

import com.elmilabs.scooters.scooterListing.model.entities.ScootersResponse
import com.elmilabs.scooters.scooterListing.model.repository.IRetrofitScooterDataSource
import io.reactivex.Observable


class RetrofitScooterDataSource constructor(private val api: RestClient): IRetrofitScooterDataSource{
    override fun loadScooters(): Observable<ScootersResponse> {
        return api.getScooterList()

    }

}
