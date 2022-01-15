package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class ParkingLot(
    @JsonProperty("parkingProvider") val parkingProvider: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("longitude") val longitude: Double,
    @JsonProperty("latitude") val latitude: Double
)
