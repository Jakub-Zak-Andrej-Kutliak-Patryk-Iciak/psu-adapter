package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitListener(
    val commandProcessor: CommandProcessor
) {

    @RabbitListener(queues = ["\${broker.refreshDataRequest}"])
    fun refreshParkingData() {
        commandProcessor.process()
    }
}
