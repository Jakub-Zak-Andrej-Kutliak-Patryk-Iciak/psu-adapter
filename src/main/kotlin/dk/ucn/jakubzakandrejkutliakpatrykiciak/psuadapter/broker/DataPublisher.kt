package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DataPublisher(
    val template: RabbitTemplate,
    @Value("\${broker.publishQueue}") val parking_service_queue: String
) {
    fun publishMessage(message: Any) {
        template.convertAndSend(parking_service_queue, message)
    }
}
