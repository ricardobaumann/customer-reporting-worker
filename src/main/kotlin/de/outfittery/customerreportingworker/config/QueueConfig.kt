package de.outfittery.customerreportingworker.config

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueueConfig(private val amqpAdmin: AmqpAdmin) {

    companion object {
        private const val HIBERNATE_DLQ = "customer-reporting.hibernate-dlq"
        private const val TASKS_DLQ = "customer-reporting.tasks-dlq"
        private const val HIBERNATE_EXCHANGE = "com.ps.topic.entity"
        private const val TASK_EXCHANGE = "com.ps.workflow"
        const val HIBERNATE_QUEUE = "customer-reporting-worker.hibernate"
        const val TASKS_QUEUE = "customer-reporting.tasks"
    }

    @Bean
    fun hibernateDeadLetterQueue() = Queue(HIBERNATE_DLQ, true)
            .also { amqpAdmin.declareQueue(it) }

    @Bean
    fun tasksDeadLetterQueue() = Queue(TASKS_DLQ, true)
            .also { amqpAdmin.declareQueue(it) }

    @Bean
    fun hibernateExchange() = DirectExchange(HIBERNATE_EXCHANGE).also { amqpAdmin.declareExchange(it) }

    @Bean
    fun hibernateQueue() = Queue(HIBERNATE_QUEUE,
            true, false, false,
            mapOf(
                    "x-dead-letter-exchange" to HIBERNATE_EXCHANGE,
                    "x-dead-letter-routing-key" to HIBERNATE_DLQ
            )).also { amqpAdmin.declareQueue(it) }

    @Bean
    fun hibernateBinding(hibernateExchange: Exchange, hibernateQueue: Queue): Binding = BindingBuilder.bind(hibernateQueue)
            .to(hibernateExchange)
            .with("hibernate")
            .noargs().also { amqpAdmin.declareBinding(it) }


    @Bean
    fun taskEventQueue(): Queue = Queue(
            TASKS_QUEUE,
            true, false, false,
            mapOf(
                    "x-dead-letter-exchange" to TASK_EXCHANGE,
                    "x-dead-letter-routing-key" to TASKS_DLQ
            )
    ).also {
        amqpAdmin.declareQueue(it)
    }

    @Bean
    fun taskExchange(): Exchange = DirectExchange(TASK_EXCHANGE)
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