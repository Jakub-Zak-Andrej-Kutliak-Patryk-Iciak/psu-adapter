package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class BrokerConfig {

    @Bean
    fun connection(): Connection {
        val factory = ConnectionFactory()
        factory.host = "localhost"
        factory.port = 5673
        return factory.newConnection()
    }
}
