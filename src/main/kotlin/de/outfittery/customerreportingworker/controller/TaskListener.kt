package de.outfittery.customerreportingworker.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.outfittery.customerreportingworker.model.StylistTask
import de.outfittery.customerreportingworker.repo.TaskRepo
import mu.KLogging
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class TaskListener(private val taskRepo: TaskRepo,
                   private val objectMapper: ObjectMapper) {

    companion object : KLogging()

    @RabbitListener(queues = ["customer-reporting.tasks"], concurrency = "5-20")
    fun handle(message: String) {
        logger.info("Handling task event: $message")
        objectMapper.readValue(message, StylistTask::class.java)
                ?.also { taskRepo.save(it.apply { it.customerId = it.variables["customerId"] as String? }) }
    }

}