package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RabbitListener(
    val commandProcessor: CommandProcessor,
    @Value("\${broker.refreshDataRequest}") val refreshDataRequestQueue: String
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @RabbitListener(queues = ["\${broker.refreshDataRequest}"])
    fun refreshParkingData() {
        logger.info("New data received from $refreshDataRequestQueue")
        commandProcessor.process()
    }
}
