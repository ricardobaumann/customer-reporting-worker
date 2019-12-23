package de.outfittery.customerreportingworker.config

import org.springframework.beans.factory.DisposableBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Configuration
class ThreadPoolConfig : DisposableBean {

    private val executorService = Executors.newFixedThreadPool(200)

    @Bean
    fun executorService(): ExecutorService = executorService

    override fun destroy() {
        executorService.shutdown()
    }

}