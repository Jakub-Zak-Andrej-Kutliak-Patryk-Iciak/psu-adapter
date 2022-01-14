package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.client.PsuService
import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.model.ParkingLot
import org.springframework.stereotype.Component

@Component
class CommandProcessor(
    val messagePublisher: MessagePublisher,
    val psuService: PsuService,
) {

    fun process(): Array<ParkingLot> {
        val parkingData = psuService.getParkingLots()
        messagePublisher.publishMessage(parkingData)
        return parkingData
    }
}
