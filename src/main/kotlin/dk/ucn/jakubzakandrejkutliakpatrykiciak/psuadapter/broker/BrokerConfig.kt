package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BrokerConfig {
    companion object {
        const val PSU_REFRESH_COMMAND_QUEUE = "psu-command-queue"
        const val PARKING_SERVICE_QUEUE = "parking-service-queue"
    }

    @Bean
    fun commandQueue(): Queue {
        return Queue(PSU_REFRESH_COMMAND_QUEUE);
    }

    @Bean
    fun parkingServiceQueue(): Queue {
        return Queue(PARKING_SERVICE_QUEUE);
    }

    @Bean
    fun converter(): MessageConverter {
        return Jackson2JsonMessageConverter();
    }
}
