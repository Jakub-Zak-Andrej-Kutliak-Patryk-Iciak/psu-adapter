package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BrokerConfig(
    @Value("\${broker.commandQueue}") val psu_command_queue: String,
    @Value("\${broker.publishQueue}") val parking_service_queue: String
) {
    @Bean
    fun commandQueue(): Queue {
        return Queue(psu_command_queue);
    }

    @Bean
    fun parkingServiceQueue(): Queue {
        return Queue(parking_service_queue);
    }

    @Bean
    fun converter(): MessageConverter {
        return Jackson2JsonMessageConverter();
    }
}
