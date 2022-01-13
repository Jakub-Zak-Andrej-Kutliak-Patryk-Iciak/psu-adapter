package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.client.PsuService
import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.model.ParkingLot
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CommandProcessor(
    val dataPublisher: DataPublisher,
    val psuService: PsuService,
) {
    val logger = LoggerFactory.getLogger(CommandProcessor::class.java)

    fun process(): Array<ParkingLot> {
        val parkingData = psuService.getParkingLots()
        dataPublisher.publishMessage(parkingData)
        logger.info("New parking data has been published: $parkingData")
        return parkingData
    }
}
