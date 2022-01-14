package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.client.PsuService
import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.model.ParkingLot
import org.springframework.stereotype.Component

@Component
class CommandProcessor(
    val messageProducer: MessageProducer,
    val psuService: PsuService,
) {

    fun process(): Array<ParkingLot> {
        val parkingData = psuService.getParkingLots()
        messageProducer.publishMessage(parkingData)
        return parkingData
    }
}
