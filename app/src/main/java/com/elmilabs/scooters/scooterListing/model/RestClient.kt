package com.elmilabs.scooters.scooterListing.model

import com.elmilabs.scooters.scooterListing.model.entities.ScootersResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RestClient {


    @GET("/prod/scooters")
    fun getScooterList(): Observable<ScootersResponse>
}
