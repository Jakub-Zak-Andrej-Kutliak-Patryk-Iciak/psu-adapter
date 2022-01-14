package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException
import java.nio.charset.StandardCharsets

@Component
class MessageConsumer(
    @Value("\${broker.refreshDataRequest}") private val refreshDataRequestQueue: String,
    private val commandProcessor: CommandProcessor,
    connection: Connection
) : Runnable {
    private val channel: Channel
    private val logger = LoggerFactory.getLogger(this.javaClass)
    override fun run() {
        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val message = String(delivery.body, StandardCharsets.UTF_8)
            println("Received '$message'")
            logger.info("Received command from \$refreshDataRequestQueue")
            commandProcessor.process()
        }
        try {
            channel.basicConsume(refreshDataRequestQueue, true, deliverCallback) { consumerTag: String? -> }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    init {
        channel = connection.createChannel()
        channel.queueDeclare(refreshDataRequestQueue, false, false, false, null)
    }
}
