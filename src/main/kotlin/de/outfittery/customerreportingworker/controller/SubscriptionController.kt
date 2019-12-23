package de.outfittery.customerreportingworker.controller

import de.outfittery.customerreportingworker.model.SubscriptionEvent
import de.outfittery.customerreportingworker.service.SubscriptionService
import mu.KLogging
import org.springframework.stereotype.Component

@Component
class SubscriptionController(private val subscriptionService: SubscriptionService) {

    companion object : KLogging()

    fun putSubscriptionEvent(entityId: String, subscriptionEvent: SubscriptionEvent) {
        try {
            subscriptionService.indexSubscriptionEvent(entityId, subscriptionEvent)
        } catch (e: Exception) {
            logger.warn("Failed to handle subscription event $subscriptionEvent", e)
        }
    }

}