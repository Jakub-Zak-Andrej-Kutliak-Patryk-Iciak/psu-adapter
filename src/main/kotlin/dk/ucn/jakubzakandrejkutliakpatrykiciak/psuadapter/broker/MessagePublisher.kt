package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MessagePublisher(
    val template: RabbitTemplate,
    @Value("\${broker.refreshDataResponse}") val refreshDataResponseQueue: String
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun publishMessage(message: Any) {
        template.convertAndSend(refreshDataResponseQueue, message)
        logger.info("New data published to $refreshDataResponseQueue")
    }
}
