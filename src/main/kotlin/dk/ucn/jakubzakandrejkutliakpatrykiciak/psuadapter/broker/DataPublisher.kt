package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class DataPublisher(
    val template: RabbitTemplate
) {
    fun publishMessage(message: Any) {
        template.convertAndSend(BrokerConfig.PARKING_SERVICE_QUEUE, message)
    }
}
