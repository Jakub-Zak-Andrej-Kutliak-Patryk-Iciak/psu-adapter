package dk.ucn.jakubzakandrejkutliakpatrykiciak.psuadapter.client

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.time.Duration


@Configuration
class WebClientConfig {
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        val restTemplate = builder
            .setConnectTimeout(Duration.ofMillis(3000))
            .setReadTimeout(Duration.ofMillis(3000))
            .build()
        val messageConverters: MutableList<HttpMessageConverter<*>> = ArrayList()
        val converter = MappingJackson2HttpMessageConverter()
        converter.supportedMediaTypes = listOf(MediaType.ALL)
        messageConverters.add(converter)
        restTemplate.messageConverters = messageConverters
        return restTemplate
    }
}
