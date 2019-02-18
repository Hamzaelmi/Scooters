package com.elmilabs.scooters.scooterListing.model.entities


data class Scooter(
    val distance_to_travel: Double,
    val energy_level: Int,
    val id: String,
    val license_plate: String,
    val location: Location,
    val market_id: String,
    val model: String,
    val vin: String
)

