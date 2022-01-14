package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BrokerConfig(
    @Value("\${broker.refreshDataRequest}") val psuCommandQueue: String,
    @Value("\${broker.refreshDataResponse}") val parkingServiceQueue: String
) {
    @Bean
    fun commandQueue(): Queue {
        return Queue(psuCommandQueue)
    }

    @Bean
    fun parkingServiceQueue(): Queue {
        return Queue(parkingServiceQueue)
    }

    @Bean
    fun converter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }
}
