package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MessagePublisher(
    val template: RabbitTemplate,
    @Value("\${broker.refreshDataResponse}") val parkingServiceQueue: String
) {
    fun publishMessage(message: Any) {
        template.convertAndSend(parkingServiceQueue, message)
    }
}
