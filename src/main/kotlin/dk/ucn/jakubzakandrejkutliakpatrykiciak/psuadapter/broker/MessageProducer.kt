package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.model.ParkingLot
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class MessageProducer(
    @Value("\${broker.refreshDataResponse}") private val refreshDataResponseQueue: String,
    connection: Connection
) {
    private val channel: Channel
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Throws(IOException::class)
    fun publishMessage(message: Array<ParkingLot>) {
        val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()
        val json = objectWriter.writeValueAsString(message)
        channel.basicPublish("", refreshDataResponseQueue, null, json.toByteArray())
        logger.info("New data published to " + refreshDataResponseQueue + ": " + message.size + " parking lots")
    }

    init {
        channel = connection.createChannel()
    }
}
