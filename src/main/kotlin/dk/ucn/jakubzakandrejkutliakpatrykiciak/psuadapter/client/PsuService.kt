package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.client

import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.model.ParkingLot
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.convert.TypeDescriptor.array
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*
import java.util.stream.Collectors

@Component
class PsuService(
    @Value("\${psu.getAll}") private val psuUrl: String,
    private val restTemplate: RestTemplate
) {
    fun getParkingLots(): Array<ParkingLot> {
        return try {
            val parkingLots = restTemplate.getForEntity(psuUrl, Array<PsuParkingLot>::class.java).body ?: throw RuntimeException()
            Arrays.stream(parkingLots).map { psuParking -> ParkingLot("psu", psuParking.name, psuParking.coord.split(",")[0].toDouble(), psuParking.coord.split(",")[1].toDouble()) }
                .collect(Collectors.toList()).toTypedArray()
        } catch (e: java.lang.RuntimeException) {
            arrayOf(ParkingLot("PSU Parking Provider", "UCN Parking", 50.0, 50.0))
        }
    }
}
