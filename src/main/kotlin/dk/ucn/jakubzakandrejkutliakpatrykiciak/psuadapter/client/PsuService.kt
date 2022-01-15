package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.client

import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.dto.ParkingLot
import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.dto.PsuParkingLot
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*
import java.util.stream.Collectors

@Component
class PsuService(
    @Value("\${psu.getAll}") private val psuUrl: String,
    private val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun getParkingLots(): Array<ParkingLot> {
        return try {
            val parkingLots = restTemplate.getForEntity(psuUrl, Array<PsuParkingLot>::class.java).body ?: throw RuntimeException()
            logger.info("Received parking data - ${parkingLots.size} element(s)")
            Arrays.stream(parkingLots).map { psuParking -> ParkingLot(
                "psu",
                psuParking.name,
                psuParking.coord.split(",")[0].toDouble(),
                psuParking.coord.split(",")[1].toDouble(),
                psuParking.max.toInt(),
                psuParking.current.toInt()
            ) }.collect(Collectors.toList()).toTypedArray()
        } catch (e: java.lang.RuntimeException) {
            logger.error("Failure trying to get PSU Service data")
            throw e
        }
    }
}
