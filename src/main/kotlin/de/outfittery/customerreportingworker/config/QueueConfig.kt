package de.outfittery.customerreportingworker.config

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueueConfig(private val amqpAdmin: AmqpAdmin) {

    @Bean
    fun hibernateExchange() = DirectExchange("com.ps.topic.entity").also { amqpAdmin.declareExchange(it) }

    @Bean
    fun hibernateQueue() = Queue("customer-reporting-worker.hibernate",
            true, false, false).also { amqpAdmin.declareQueue(it) }

    @Bean
    fun hibernateBinding(hibernateExchange: Exchange, hibernateQueue: Queue): Binding = BindingBuilder.bind(hibernateQueue)
            .to(hibernateExchange)
            .with("hibernate")
            .noargs().also { amqpAdmin.declareBinding(it) }


    @Bean
    fun taskEventQueue(): Queue = Queue(
            "customer-reporting.tasks",
            true, false, false).also {
        amqpAdmin.declareQueue(it)
    }

    @Bean
    fun taskExchange(): Exchange = DirectExchange("com.ps.workflow")
            .also { amqpAdmin.declareExchange(it) }

    @Bean
    fun taskBinding(taskEventQueue: Queue, taskExchange: Exchange): Binding = BindingBuilder
            .bind(taskEventQueue)
            .to(taskExchange)
            .with("generic")
            .noargs().also {
                amqpAdmin.declareBinding(it)
            }

}