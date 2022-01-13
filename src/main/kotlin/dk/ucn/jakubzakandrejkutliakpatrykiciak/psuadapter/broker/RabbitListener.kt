package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitListener(
    val commandProcessor: CommandProcessor
) {

    @RabbitListener(queues = [BrokerConfig.PSU_REFRESH_COMMAND_QUEUE])
    fun refreshParkingData() {
        commandProcessor.process()
    }
}
