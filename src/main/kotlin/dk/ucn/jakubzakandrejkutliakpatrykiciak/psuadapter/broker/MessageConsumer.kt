package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.broker

import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets


@Component
class MessageConsumer(
    @Value("\${broker.refreshDataRequest}") private val refreshDataRequestQueue: String,
    private val commandProcessor: CommandProcessor,
    private val connection: Connection
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Async
    @EventListener(ApplicationStartedEvent::class)
    fun consumeRefreshDataRequestQueue() {
        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val message = String(delivery.body, StandardCharsets.UTF_8)
            logger.info("Received command '$message' from ${refreshDataRequestQueue}")
            try {
                commandProcessor.process()
            } catch (e: RuntimeException) {
                logger.error(e.message)
            }
        }
        val channel = connection.createChannel()
        channel.queueDeclare(refreshDataRequestQueue, true, false, false, null)
        logger.info("Starting consuming...")
        channel.basicConsume(refreshDataRequestQueue, true, deliverCallback) { consumerTag: String -> }
    }
}
