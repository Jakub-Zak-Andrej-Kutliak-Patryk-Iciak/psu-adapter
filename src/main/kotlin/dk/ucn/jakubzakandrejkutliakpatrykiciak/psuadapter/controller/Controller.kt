package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.controller

import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker.CommandProcessor
import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.dto.ParkingLot
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
@CrossOrigin(origins = ["*"])
class Controller(
    val commandProcessor: CommandProcessor
) {
    @GetMapping("refresh")
    fun triggerRefresh(): Array<ParkingLot> {
        return commandProcessor.process()
    }
}
