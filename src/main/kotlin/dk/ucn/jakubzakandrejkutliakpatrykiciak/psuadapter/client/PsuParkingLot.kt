package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.client

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class PsuParkingLot (
    @JsonProperty("date") val date: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("coord") val coord: String,
    @JsonProperty("max") val max: String,
    @JsonProperty("current") val current: String
)
