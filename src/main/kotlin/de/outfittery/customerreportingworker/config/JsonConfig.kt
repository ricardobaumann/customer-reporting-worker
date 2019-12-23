package de.outfittery.customerreportingworker.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonConfig {

    @Bean
    fun objectMapper() = ObjectMapper().apply {
        this.registerModule(KotlinModule())
        this.registerModule(JavaTimeModule())
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    }
}
