package com.elmilabs.scooters.scooterListing.model.entities


data class ScootersResponse(
    val meta: Meta,
    val data: Data
)

data class Data(
    val scooters: List<Scooter>
)
data class Meta(
    val key: String,
    val server_time: String,
    val status: Int
)


